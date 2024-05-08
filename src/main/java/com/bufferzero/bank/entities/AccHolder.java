package com.bufferzero.bank.entities;

import com.bufferzero.bank.auto.CustomAccNoGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "account_holders")
public class AccHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long accNo;

    private String name;
    private String mobileNo;

    private double accBalance;

    public AccHolder() {
    }

    public AccHolder( String name, String mobileNo, double accBalance) {
        this.accNo = CustomAccNoGenerator.genarateAccNo();
        this.name = name;
        this.mobileNo = mobileNo;
        this.accBalance = accBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getAccNo() {
        return accNo;
    }

    public void setAccNo(long accNo) {
        this.accNo = accNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }
}
