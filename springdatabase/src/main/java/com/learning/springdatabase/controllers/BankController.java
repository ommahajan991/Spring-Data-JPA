package com.learning.springdatabase.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public BankDto getBank(@PathVariable int id) {
		return bankService.getBank(id);
	}

	@GetMapping
	public List<BankDto> getAllBanks() {
		return bankService.getAllBanks();
	}

	@PostMapping
	public BankDto createBank(@RequestBody BankDto bankDto) {
		return bankService.createBank(bankDto);
	}

	@PutMapping("/{id}")
	public BankDto updateBank(@PathVariable int id, @RequestBody BankDto bankDto) {
		return bankService.updateBank(id, bankDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteBank(@PathVariable int id) {
		bankService.deleteBank(id);
	}
	
	@DeleteMapping
	public void deleteAllBank() {
		bankService.deleteAllBanks();
	}
}