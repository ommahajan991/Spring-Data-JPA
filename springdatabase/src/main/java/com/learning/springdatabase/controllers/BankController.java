package com.learning.springdatabase.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.services.BankService;

@RestController
@RequestMapping("/banks")
public class BankController {

	private BankService bankService;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	/** Handler Methods */

	@GetMapping("/{id")
	public BankDto getBank(@PathVariable int id) {
		return bankService.getBank(id);
	}
}