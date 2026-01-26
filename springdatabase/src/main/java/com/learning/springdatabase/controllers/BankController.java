package com.learning.springdatabase.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.learning.springdatabase.services.BankService;

@RestController
public class BankController {

	private BankService bankService;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	/** Handler Methods */

}