package com.learning.springdatabase.services;

import java.util.List;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.dto.UpdateBankTypeRequestDto;

public interface BankService {

	/** Abstract Service Methods */

	public BankDto getBank(int id);

	public List<BankDto> getAllBanks();
	
	public BankDto getBankByifscPrefix(String ifscPrefix);
	
	public BankDto getBankByNameAndType(String name, String type);

	public BankDto createBank(BankDto bankDto);

	public BankDto updateBank(int id, BankDto bankDto);
	
	public BankDto updateBankType(int id, UpdateBankTypeRequestDto dto);

	public void deleteBank(int id);

	public void deleteAllBanks();

}