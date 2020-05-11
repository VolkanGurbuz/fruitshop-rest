package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTOList;

public interface VendorService {

  VendorDTO getVendorById(Long id);

  VendorDTOList getAllVendors();

  VendorDTO createNewVendor(VendorDTO vendorDTO);

  VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO);

  VendorDTO patchVendor(Long id, VendorDTO vendorDTO);

  void deleteVendorById(Long id);
}
