package com.volkangurbuz.fruitshoprest.api.v1.model;

import com.volkangurbuz.fruitshoprest.domain.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTOList {

  List<VendorDTO> vendorDTOS;
}
