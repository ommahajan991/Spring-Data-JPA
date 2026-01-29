package com.learning.springdatabase.services;

import java.util.List;

import com.learning.springdatabase.dto.BankDto;

public interface BankService {

	/** Abstract Service Methods */

	public BankDto getBank(int id);

	public List<BankDto> getAllBanks();

	public BankDto createBank(BankDto bankDto);

	public BankDto updateBank(int id, BankDto bankDto);

	public void deleteBank(int id);

	public void deleteAllBanks();

}