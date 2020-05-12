package com.volkangurbuz.fruitshoprest.controller.v1;

import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryListDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerListDTO;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

  public static final String BASE_URL = "/api/v1/customers";
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @ApiOperation(
      value = "This will get a list of customers",
      notes = "there are some notes about the api")
  @GetMapping
  public CustomerListDTO getAllCustomers() {

    return new CustomerListDTO(customerService.getAllCustomers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerDTO> getCategoryByName(@PathVariable Long id) {

    return new ResponseEntity<CustomerDTO>(customerService.getCustomerById(id), HttpStatus.OK);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerDTO createNewCustomer(@RequestBody CustomerDTO customerDTO) {
    return customerService.createNewCustomer(customerDTO);
  }

  @PutMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    return customerService.saveCustomerByDTO(id, customerDTO);
  }

  @PatchMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public CustomerDTO patchCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
    return customerService.patchCustomer(id, customerDTO);
  }

  @DeleteMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteCustomer(@PathVariable Long id) {
    customerService.deleteCustomerByID(id);
  }
}
