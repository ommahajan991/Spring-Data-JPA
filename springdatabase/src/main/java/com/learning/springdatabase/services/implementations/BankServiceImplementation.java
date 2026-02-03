package com.learning.springdatabase.services.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springdatabase.dto.BankDto;
import com.learning.springdatabase.dto.UpdateBankTypeRequestDto;
import com.learning.springdatabase.entities.Bank;
import com.learning.springdatabase.exceptionHandlers.ResourceNotFoundException;
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
		Bank bank = bankRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Bank Not Found"));
		return mapper.toDto(bank);
	}

	@Override
	public List<BankDto> getAllBanks() {
		List<Bank> banks = bankRepository.findAll();
		return banks.stream().map(mapper::toDto).toList();
	}

	@Override
	public BankDto getBankByifscPrefix(String ifscPrefix) {

		Optional<Bank> bank = bankRepository.getByifscPrefix(ifscPrefix);
		if (bank.isPresent()) {
			return mapper.toDto(bank.get());
		}
		return null;
	}
	
	@Override
	public BankDto getBankByNameAndType(String name, String type) {
	Bank bank = bankRepository.getBankByNameAndType(name, type);
		return mapper.toDto(bank);
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
		existingBank.setType(bankDto.getType());

		Bank updatedBank = bankRepository.save(existingBank);
		return mapper.toDto(updatedBank);
	}

	@Override
	public BankDto updateBankType(int id, UpdateBankTypeRequestDto dto) {
		Bank existingbank = bankRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank Not Found"));

		existingbank.setType(dto.getType());

		Bank updatedBank = bankRepository.save(existingbank);
		return mapper.toDto(updatedBank);
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