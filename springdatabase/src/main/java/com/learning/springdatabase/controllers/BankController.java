package com.learning.springdatabase.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.dto.BankResponseDto;
import com.learning.springdatabase.dto.UpdateBankTypeRequestDto;
import com.learning.springdatabase.services.BankService;

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/banks")
public class BankController {

	private BankService bankService;

	public BankController(BankService bankService) {
		this.bankService = bankService;
	}

	/** Handler Methods */

	@GetMapping("/{id}")
	public ResponseEntity<BankResponseDto<BankDto>> getBank(@PathVariable int id) {
		BankResponseDto<BankDto> dto = BankResponseDto.<BankDto>builder().status(HttpStatus.OK.value())
				.message("Bank Fetched Successfully").data(bankService.getBank(id)).build();
		return ResponseEntity.ok(dto);
	}

	@GetMapping
	public ResponseEntity<List<BankDto>> getAllBanks() {
		return ResponseEntity.ok(bankService.getAllBanks());
	}

	@GetMapping("/bank-ifscPrefix/{ifscPrefix}")
	public ResponseEntity<BankResponseDto<BankDto>> getBankByifscPrefix(@PathVariable String ifscPrefix) {

		BankResponseDto<BankDto> dto = new BankResponseDto<>();
		BankDto bank = bankService.getBankByifscPrefix(ifscPrefix);

		if (bank != null) {
			dto.setStatus(HttpStatus.OK.value());
			dto.setMessage("Bank Fetched Successfully");
			dto.setData(bank);
			return ResponseEntity.ok(dto);
		} else {
			dto.setStatus(HttpStatus.NOT_FOUND.value());
			dto.setMessage("Bank Not Found");
			dto.setData(null);
			return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("name-type/{name}/{type}")
	public ResponseEntity<BankDto> getBankByNameAndType(String name, String type) {
		return ResponseEntity.ok(bankService.getBankByNameAndType(name, type));
	}

	@GetMapping("/pagination")
	public ResponseEntity<List<BankDto>> getBanks(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size,
			@RequestParam(defaultValue = "name") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection) {
		
		Sort sort = sortDirection.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		Pageable pageable = PageRequest.of(page, size, sort);
		Page<BankDto> banks = bankService.findBanks(pageable);
		return ResponseEntity.ok(banks.toList());
	}

	@PostMapping
	public ResponseEntity<BankDto> createBank(@RequestBody BankDto bankDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(bankService.createBank(bankDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BankDto> updateBank(@PathVariable int id, @RequestBody BankDto bankDto) {
		return ResponseEntity.ok(bankService.updateBank(id, bankDto));
	}
	
	@PostMapping("/multiple-insert")
	public ResponseEntity<List<BankDto>> insertMultipleBanks(){
		return ResponseEntity.ok(bankService.addBulkBanks());
	}

	@PatchMapping("/{id}")
	public ResponseEntity<BankDto> updateBankType(@PathVariable int id, @RequestBody UpdateBankTypeRequestDto dto) {
		return ResponseEntity.ok(bankService.updateBankType(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBank(@PathVariable int id) {
		bankService.deleteBank(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteAllBank() {
		bankService.deleteAllBanks();
		;
		return ResponseEntity.noContent().build();
	}
}