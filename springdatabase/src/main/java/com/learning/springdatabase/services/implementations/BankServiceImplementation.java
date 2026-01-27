package com.learning.springdatabase.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.repositories.BankRepository;
import com.learning.springdatabase.services.BankService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImplementation implements BankService {

	@Autowired
	private final BankRepository bankRepository;

	/** Overridden Service Methods */

	@Override
	public BankDto getBank(int id) {
		return null;
	}

	@Override
	public List<BankDto> getAllBanks() {
		return null;
	}

	@Override
	public BankDto createBank(BankDto bankDto) {
		return null;
	}

	@Override
	public BankDto updateBank(int id, BankDto bankDto) {
		return null;
	}

	@Override
	public void deleteBank(int id) {

	}

	@Override
	public void deleteAllBanks(int id) {

	}
}