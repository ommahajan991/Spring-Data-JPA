package com.learning.springdatabase.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.entities.Bank;
import com.learning.springdatabase.mappers.BankMapper;
import com.learning.springdatabase.repositories.BankRepository;
import com.learning.springdatabase.services.BankService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImplementation implements BankService {

	@Autowired
	private final BankRepository bankRepository;
	private final BankMapper mapper;

	/** Overridden Service Methods */

	@Override
	public BankDto getBank(int id) {
		Bank bank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank Not Found"));
		return mapper.toDto(bank);
	}

	@Override
	public List<BankDto> getAllBanks() {
		List<Bank> banks = bankRepository.findAll();
		return banks.stream().map(mapper::toDto).toList();
	}

	@Override
	public BankDto createBank(BankDto bankDto) {
		Bank newBank = bankRepository.save(mapper.toEntity(bankDto));
		return mapper.toDto(newBank);
	}

	@Override
	public BankDto updateBank(int id, BankDto bankDto) {
		Bank existingBank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank Not Found"));

		existingBank.setName(bankDto.getName());
		existingBank.setIfscPrefix(bankDto.getIfscPrefix());
		existingBank.getType();

		Bank updatedBAnk = bankRepository.save(existingBank);
		return mapper.toDto(updatedBAnk);
	}

	@Override
	public void deleteBank(int id) {
		bankRepository.deleteById(id);
	}

	@Override
	public void deleteAllBanks() {
		bankRepository.deleteAll();
	}
}