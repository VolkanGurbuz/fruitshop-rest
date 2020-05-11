package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.mapper.CustomerMapper;
import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

  private CustomerMapper customerMapper;
  private CustomerRepository customerRepository;

  @Autowired
  public void setCustomerMapper(CustomerMapper customerMapper) {
    this.customerMapper = customerMapper;
  }

  @Autowired
  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(
            customer -> {
              CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());
              return customerDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    return customerRepository
        .findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .orElseThrow(RuntimeException::new); // todo implement better exception handling
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

    return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {

    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);

    return saveAndReturnDTO(customer);
  }

  private CustomerDTO saveAndReturnDTO(Customer customer) {

    Customer savedCustomer = customerRepository.save(customer);
    CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(savedCustomer);

    customerDTO.setCustomerUrl("/api/v1/customer" + savedCustomer.getId());
    return customerDTO;
  }
}
