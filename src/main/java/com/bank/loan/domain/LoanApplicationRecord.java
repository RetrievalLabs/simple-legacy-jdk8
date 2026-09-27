package com.bank.loan.domain;

import java.math.BigDecimal;
import java.util.Date;

public class LoanApplicationRecord {
    private Long id;
    private String applicationNumber;
    private Long customerId;
    private BigDecimal amount;
    private int tenureMonths;
    private BigDecimal annualIncome;
    private BigDecimal existingMonthlyDebt;
    private String purpose;
    private LoanStatus status = LoanStatus.DRAFT;
    private String approvalStatus;
    private BigDecimal monthlyEmi;
    private Date createdAt = new Date();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getApplicationNumber() { return applicationNumber; }
    public void setApplicationNumber(String applicationNumber) { this.applicationNumber = applicationNumber; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public int getTenureMonths() { return tenureMonths; }
    public void setTenureMonths(int tenureMonths) { this.tenureMonths = tenureMonths; }
    public BigDecimal getAnnualIncome() { return annualIncome; }
    public void setAnnualIncome(BigDecimal annualIncome) { this.annualIncome = annualIncome; }
    public BigDecimal getExistingMonthlyDebt() { return existingMonthlyDebt; }
    public void setExistingMonthlyDebt(BigDecimal existingMonthlyDebt) { this.existingMonthlyDebt = existingMonthlyDebt; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public LoanStatus getStatus() { return status; }
    public void setStatus(LoanStatus status) { this.status = status; }
    public String getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(String approvalStatus) { this.approvalStatus = approvalStatus; }
    public BigDecimal getMonthlyEmi() { return monthlyEmi; }
    public void setMonthlyEmi(BigDecimal monthlyEmi) { this.monthlyEmi = monthlyEmi; }
    public Date getCreatedAt() { return createdAt; }
}
