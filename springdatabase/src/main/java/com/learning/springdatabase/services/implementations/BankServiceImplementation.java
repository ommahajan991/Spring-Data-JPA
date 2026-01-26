package com.learning.springdatabase.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.springdatabase.repositories.BankRepository;
import com.learning.springdatabase.services.BankService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankServiceImplementation implements BankService {

	@Autowired
	private final BankRepository bankRepository;

	/** Overridden Service Methods */
}