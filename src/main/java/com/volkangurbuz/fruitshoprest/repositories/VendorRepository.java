package com.volkangurbuz.fruitshoprest.repositories;

import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTOList;
import com.volkangurbuz.fruitshoprest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {}
