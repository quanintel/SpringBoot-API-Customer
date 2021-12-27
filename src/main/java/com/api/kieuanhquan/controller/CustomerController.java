package com.api.kieuanhquan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.kieuanhquan.models.Customer;
import com.api.kieuanhquan.services.CustomerServices;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class CustomerController {

	@Autowired
	private CustomerServices customerServices;

	// GET ALL
	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> list(@RequestParam(required = false) String name) {
		try {
			List<Customer> customerList = new ArrayList<Customer>();
			if (name == null)
				customerServices.listAll().forEach(customerList::add);
			else
				customerServices.getByName(name).forEach(customerList::add);

			if (customerList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			 return new ResponseEntity<>(customerList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET BY ID
	@GetMapping("/customer/{id}")
	public ResponseEntity<Customer> getById(@PathVariable Integer id) {
		try {
			Customer customer = customerServices.getById(id);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}

	// POST = CREATE
	@PostMapping("/customer")
	public void add(@RequestBody Customer customer) {
		customerServices.save(customer);
	}

	// PUT = UPDATE
	@PutMapping("/customer/{id}")
	public ResponseEntity<?> update(@RequestBody Customer customer, @PathVariable Integer id) {
		try {
			customerServices.save(customer);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// DELETE
	@DeleteMapping("/customer/{id}")
	public void delete(@PathVariable Integer id) {
		customerServices.delete(id);
	}

	// DELETE ALL
	@DeleteMapping("/customer")
	public void deleteAll() {
		customerServices.deleteAll();
	}

}
