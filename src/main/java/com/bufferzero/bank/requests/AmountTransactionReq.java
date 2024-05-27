package com.bufferzero.bank.requests;

public class AmountTransactionReq {

    private double amt;

    private String typeOfOp;



    public double getAmt() {
        return amt;
    }

    public void setAmt(double amt) {
        this.amt = amt;
    }

    public String getTypeOfOp() {
        return typeOfOp;
    }

    public void setTypeOfOp(String typeOfOp) {
        this.typeOfOp = typeOfOp;
    }
}
