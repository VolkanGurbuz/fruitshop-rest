package com.volkangurbuz.fruitshoprest.repositories;

import com.volkangurbuz.fruitshoprest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {}
