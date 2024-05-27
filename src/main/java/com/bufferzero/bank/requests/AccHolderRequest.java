package com.bufferzero.bank.requests;

import jakarta.persistence.Lob;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class AccHolderRequest {
    @NotBlank(message = "Name shouldn't be empty")
    private String firstName;
    @NotBlank(message = "Last name shouldn't be empty")
    private String lastName;
    @Pattern(regexp = "^[6789]\\d{9}$", message = "Invalid mobile number")
    private String mobileNo;
    @Min(value = 500, message = "minimum balance amount is Rs.500")
    private double accBalance;

    private String typeofOperation;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email id")
    private String email;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getTypeofOperation() {
        return typeofOperation;
    }

    public void setTypeofOperation(String typeofOperation) {
        this.typeofOperation = typeofOperation;
    }
}
