package com.api.kieuanhquan.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.kieuanhquan.models.Customer;

public interface CustomerJPA extends JpaRepository<Customer, Integer> {
	List<Customer> findByName(String name);
}
