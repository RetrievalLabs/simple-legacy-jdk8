package com.bank.loan.api;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateLoanRequest {
    @NotNull @Min(1) private Long customerId;
    @NotNull @DecimalMin("10000.00") private BigDecimal amount;
    @Min(6) private int tenureMonths;
    @NotNull @DecimalMin("1.00") private BigDecimal annualIncome;
    @NotNull @DecimalMin("0.00") private BigDecimal existingMonthlyDebt;
    @NotNull @Size(min = 3, max = 200) private String purpose;

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
}
