package com.bufferzero.bank.exceptions;

public class AccountTransactionExp extends Exception{

    private String type;
    public AccountTransactionExp(String message, String type) {
        super(message);
        this.type=type;
    }
}
