package com.bufferzero.bank.entities;

import com.bufferzero.bank.auto.CustomAccNoGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;
//import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "account_holders")
public class AccHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long accNo;

    private String firstName;

    private String lastName;
    private String mobileNo;
    private String email;
    private double accBalance;

    private String fileName;


    public AccHolder() {
    }

    public AccHolder( String firstName, String lastName,String mobileNo, double accBalance, String email, String fileName) {
        this.accNo = CustomAccNoGenerator.genarateAccNo();
        this.firstName = firstName;
        this.lastName=lastName;
        this.mobileNo = mobileNo;
        this.email = email;
        this.accBalance = accBalance;
        this.fileName = fileName;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}

