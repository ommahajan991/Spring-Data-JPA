package com.learning.springdatabase.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.springdatabase.entities.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
	public Optional<Bank> getByifscPrefix(String ifscPrefix);
}