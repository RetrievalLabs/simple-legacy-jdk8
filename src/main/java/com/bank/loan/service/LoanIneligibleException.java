package com.bank.loan.service;

public class LoanIneligibleException extends RuntimeException {
    public LoanIneligibleException(String message) { super(message); }
}
