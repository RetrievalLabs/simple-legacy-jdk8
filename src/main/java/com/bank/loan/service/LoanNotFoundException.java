package com.bank.loan.service;

public class LoanNotFoundException extends RuntimeException {
    public LoanNotFoundException(Long id) { super("Loan application " + id + " was not found"); }
}
