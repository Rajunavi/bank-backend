package com.bufferzero.bank;

import com.bufferzero.bank.repository.AccHolderRepo;
import com.bufferzero.bank.services.AccHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

	}


