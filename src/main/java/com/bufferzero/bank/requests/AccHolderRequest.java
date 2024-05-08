package com.bufferzero.bank.requests;

import jakarta.validation.constraints.*;

public class AccHolderRequest {
    @NotBlank(message = "Name shouldn't be empty")
    private String name;
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile number")
    private String mobileNo;
    @Min(value = 500, message = "minimum balance amount is Rs.500")
    private double accBalance;

    @Email
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
