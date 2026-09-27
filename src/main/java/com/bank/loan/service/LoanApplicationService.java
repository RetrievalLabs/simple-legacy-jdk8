package com.bank.loan.service;

import com.bank.loan.api.CreateLoanRequest;
import com.bank.loan.domain.LoanApplicationRecord;
import com.bank.loan.domain.LoanStatus;
import com.bank.loan.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

@Service
public class LoanApplicationService {
    private static final BigDecimal ANNUAL_RATE = new BigDecimal("0.105");
    private static final BigDecimal MAX_DEBT_RATIO = new BigDecimal("0.45");
    private static final BigDecimal MANUAL_REVIEW_AMOUNT = new BigDecimal("1000000.00");
    private final LoanApplicationRepository repository;

    public LoanApplicationService(LoanApplicationRepository repository) { this.repository = repository; }

    public LoanApplicationRecord create(CreateLoanRequest request) {
        if (request.getTenureMonths() > 60) throw new IllegalArgumentException("Tenure cannot exceed 60 months");
        LoanApplicationRecord record = new LoanApplicationRecord();
        record.setCustomerId(request.getCustomerId());
        Long id = repository.nextId();
        record.setId(id);
        record.setApplicationNumber(String.format("APP-%06d", id));
        record.setAmount(request.getAmount());
        record.setTenureMonths(request.getTenureMonths());
        record.setAnnualIncome(request.getAnnualIncome());
        record.setExistingMonthlyDebt(request.getExistingMonthlyDebt());
        record.setPurpose(request.getPurpose().trim());
        record.setMonthlyEmi(calculateEmi(record.getAmount(), record.getTenureMonths()));
        return repository.save(record);
    }
    public List<LoanApplicationRecord> list() { return repository.findAll(); }
    public LoanApplicationRecord get(Long id) { return repository.find(id).orElseThrow(() -> new LoanNotFoundException(id)); }

    public LoanApplicationRecord submit(Long id) {
        LoanApplicationRecord record = get(id);
        if (record.getStatus() != LoanStatus.DRAFT) throw new IllegalStateException("Only draft applications can be submitted");
        BigDecimal monthlyIncome = record.getAnnualIncome().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP);
        BigDecimal debtRatio = record.getExistingMonthlyDebt().add(record.getMonthlyEmi()).divide(monthlyIncome, 4, RoundingMode.HALF_UP);
        if (debtRatio.compareTo(MAX_DEBT_RATIO) > 0) throw new LoanIneligibleException("Monthly debt including this loan exceeds 45% of income");
        record.setStatus(LoanStatus.SUBMITTED);
        record.setApprovalStatus(record.getAmount().compareTo(MANUAL_REVIEW_AMOUNT) >= 0 || debtRatio.compareTo(new BigDecimal("0.35")) >= 0 ? "MANUAL_REVIEW" : "PENDING");
        return repository.save(record);
    }

    private BigDecimal calculateEmi(BigDecimal principal, int months) {
        MathContext precision = MathContext.DECIMAL128;
        BigDecimal monthlyRate = ANNUAL_RATE.divide(new BigDecimal("12"), precision);
        BigDecimal factor = BigDecimal.ONE.add(monthlyRate).pow(months, precision);
        return principal.multiply(monthlyRate).multiply(factor)
                .divide(factor.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }
}
