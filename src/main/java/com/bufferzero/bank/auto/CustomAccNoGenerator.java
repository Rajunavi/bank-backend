package com.bufferzero.bank.auto;

public class CustomAccNoGenerator {

    private static long acc = 2200342500l;
    public static Long genarateAccNo(){
        return ++acc;
    }
}
