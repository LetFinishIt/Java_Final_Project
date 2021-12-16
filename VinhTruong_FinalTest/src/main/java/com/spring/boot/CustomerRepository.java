package com.spring.boot;

import org.springframework.data.repository.CrudRepository;

import com.spring.boot.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
