package com.xathordroid.springapitest.repository;

import com.xathordroid.springapitest.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    
    List<Customer> findByLastName(String lastName);
    
    Customer findById(long id);
}
