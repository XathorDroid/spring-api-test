package com.xathordroid.springapitest.repository;

import com.xathordroid.springapitest.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
