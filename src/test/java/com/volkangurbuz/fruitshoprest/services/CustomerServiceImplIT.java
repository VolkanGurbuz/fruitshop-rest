package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.mapper.CustomerMapper;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.bootstrap.Bootstrap;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.repositories.CategoryRepository;
import com.volkangurbuz.fruitshoprest.repositories.CustomerRepository;
import com.volkangurbuz.fruitshoprest.repositories.VendorRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplIT {

  @Autowired CustomerRepository customerRepository;

  @Autowired CategoryRepository categoryRepository;
  @Autowired VendorRepository vendorRepository;
  CustomerService customerService;

  public void setUp() throws Exception {
    System.out.println("Loading Customer Data");
    System.out.println(customerRepository.findAll().size());

    // setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
    bootstrap.run(); // load data

    customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
  }

  @Test
  public void patchCustomerUpdateFirstName() throws Exception {

    setUp();

    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);
    // save original first name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstName(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getFirstName());
    assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstName())));
    assertThat(originalLastName, equalTo(updatedCustomer.getLastName()));
  }

  @Test
  public void patchCustomerUpdateLastName() throws Exception {
    setUp();

    String updatedName = "UpdatedName";
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // save original first/last name
    String originalFirstName = originalCustomer.getFirstName();
    String originalLastName = originalCustomer.getLastName();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastName(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getLastName());
    assertThat(originalFirstName, equalTo(updatedCustomer.getFirstName()));
    assertThat(originalLastName, not(equalTo(updatedCustomer.getLastName())));
  }

  private Long getCustomerIdValue() throws Exception {
    setUp();
    List<Customer> customers = customerRepository.findAll();

    System.out.println("Customers Found: " + customers.size());

    // return first id
    return customers.get(0).getId();
  }
}
