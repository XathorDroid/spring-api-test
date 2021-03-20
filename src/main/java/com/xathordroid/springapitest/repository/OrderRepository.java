package com.xathordroid.springapitest.repository;

import com.xathordroid.springapitest.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> { }
