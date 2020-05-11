package com.volkangurbuz.fruitshoprest.controller.v1;

import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryListDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerListDTO;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/customers")
public class CustomerController {

  public static final String BASE_URL = "/api/v1/customers/";
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<CustomerListDTO> getAllCustomers() {

    return new ResponseEntity<CustomerListDTO>(
        new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCategoryByName(@PathVariable Long id) {

    return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    return new ResponseEntity<CustomerDTO>(
        customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
  }
}