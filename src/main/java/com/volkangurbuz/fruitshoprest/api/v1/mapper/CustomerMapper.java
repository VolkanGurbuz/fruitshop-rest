package com.volkangurbuz.fruitshoprest.api.v1.mapper;

import com.volkangurbuz.fruitshoprest.api.v1.model.CustomerDTO;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  CustomerDTO customerToCustomerDTO(Customer customer);

  Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
