package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.mapper.CustomerMapper;
import com.volkangurbuz.fruitshoprest.api.v1.model.CategoryDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.controller.v1.CustomerController;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

  private final CustomerMapper customerMapper;
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository) {
    this.customerMapper = customerMapper;
    this.customerRepository = customerRepository;
  }

  @Override
  public List<CustomerDTO> getAllCustomers() {
    return customerRepository.findAll().stream()
        .map(
            customer -> {
              CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);
              customerDTO.setCustomerUrl(getCustomerUrl(customer.getId()));
              return customerDTO;
            })
        .collect(Collectors.toList());
  }

  @Override
  public CustomerDTO getCustomerById(Long id) {

    return customerRepository
        .findById(id)
        .map(customerMapper::customerToCustomerDTO)
        .map(
            customerDTO -> {
              // set API URL
              customerDTO.setCustomerUrl(getCustomerUrl(id));
              return customerDTO;
            })
        .orElseThrow(RuntimeException::new); // todo implement better exception handling
  }

  @Override
  public CustomerDTO createNewCustomer(CustomerDTO customerDTO) {

    return saveAndReturnDTO(customerMapper.customerDTOToCustomer(customerDTO));
  }

  private CustomerDTO saveAndReturnDTO(Customer customer) {
    Customer savedCustomer = customerRepository.save(customer);

    CustomerDTO returnDto = customerMapper.customerToCustomerDTO(savedCustomer);

    returnDto.setCustomerUrl(getCustomerUrl(savedCustomer.getId()));

    return returnDto;
  }

  @Override
  public CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO) {
    Customer customer = customerMapper.customerDTOToCustomer(customerDTO);
    customer.setId(id);

    return saveAndReturnDTO(customer);
  }

  @Override
  public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
    return customerRepository
        .findById(id)
        .map(
            customer -> {
              if (customerDTO.getFirstName() != null) {
                customer.setFirstName(customerDTO.getFirstName());
              }

              if (customerDTO.getLastName() != null) {
                customer.setLastName(customerDTO.getLastName());
              }

              CustomerDTO returnCustomer =
                  customerMapper.customerToCustomerDTO(customerRepository.save(customer));
              returnCustomer.setCustomerUrl(getCustomerUrl(id));

              return returnCustomer;
            })
        .orElseThrow(RuntimeException::new); // todo implement better exception handling;
  }

  @Override
  public void deleteCustomerByID(Long id) {
    try {
      customerRepository.deleteById(id);
    } catch (Exception e) {
      System.err.println("please check id: " + id);
    }
  }

  private String getCustomerUrl(Long id) {
    return CustomerController.BASE_URL + "/" + id;
  }
}
