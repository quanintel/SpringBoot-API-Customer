package com.api.kieuanhquan.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.kieuanhquan.jpa.CustomerJPA;
import com.api.kieuanhquan.models.Customer;

@Service
@Transactional
public class CustomerServices {
	
	// Đặt các hàm của file Interface vào biến customerJPA
	@Autowired
	private CustomerJPA customerJPA;
	
	// Tạo các phương thức lấy dữ liệu bằng biến customerJPA
	
	// Lấy ra danh sách Customer
	public List<Customer> listAll() {
		return customerJPA.findAll();
	}
	// Lưu lại dữ liệu.
	public void save(Customer customer) {
		customerJPA.save(customer);
	}
	// Lấy dữ liêu theo id
	public Customer getById(Integer id) {
		return customerJPA.findById(id).get();
	}
	// Xoá dữ liệu theo id
	public void delete(Integer id) {
		customerJPA.deleteById(id);
	}
	
	// Xoá tất cả dữ liệu
	public void deleteAll() {
		customerJPA.deleteAll();
	}
	
	// Tìm kiếm theo name
	public List<Customer> getByName(String name) {
		return customerJPA.findByName(name);
	}
}
