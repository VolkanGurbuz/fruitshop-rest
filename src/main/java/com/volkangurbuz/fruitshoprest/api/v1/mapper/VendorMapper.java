package com.volkangurbuz.fruitshoprest.api.v1.mapper;

import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTO;
import com.volkangurbuz.fruitshoprest.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {

  VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

  VendorDTO vendorToVendorDTO(Vendor vendor);

  Vendor vendorDTOtoVendor(VendorDTO vendorDTO);
}
