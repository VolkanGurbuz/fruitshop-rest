package com.volkangurbuz.fruitshoprest.services;

import com.volkangurbuz.fruitshoprest.api.v1.mapper.VendorMapper;
import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTOList;
import com.volkangurbuz.fruitshoprest.controller.v1.VendorController;
import com.volkangurbuz.fruitshoprest.domain.Vendor;
import com.volkangurbuz.fruitshoprest.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

  private final VendorRepository vendorRepository;
  private final VendorMapper vendorMapper;

  public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
    this.vendorRepository = vendorRepository;
    this.vendorMapper = vendorMapper;
  }

  @Override
  public VendorDTO getVendorById(Long id) {

    return vendorRepository
        .findById(id)
        .map(vendorMapper::vendorToVendorDTO)
        .map(
            vendorDTO -> {
              vendorDTO.setVendorUrl(getVendorUrl(id));
              return vendorDTO;
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public VendorDTOList getAllVendors() {
    List<VendorDTO> vendorDTOS =
        vendorRepository.findAll().stream()
            .map(
                vendor -> {
                  VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                  vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                  return vendorDTO;
                })
            .collect(Collectors.toList());

    return new VendorDTOList(vendorDTOS);
  }

  @Override
  public VendorDTO createNewVendor(VendorDTO vendorDTO) {
    return saveAndReturnDTO(vendorMapper.vendorDTOtoVendor(vendorDTO));
  }

  @Override
  public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {

    Vendor vendorToSave = vendorMapper.vendorDTOtoVendor(vendorDTO);
    vendorToSave.setId(id);

    return saveAndReturnDTO(vendorToSave);
  }

  @Override
  public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
    return vendorRepository
        .findById(id)
        .map(
            vendor -> {
              // todo if more properties, add more if statements

              if (vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
              }

              return saveAndReturnDTO(vendor);
            })
        .orElseThrow(ResourceNotFoundException::new);
  }

  @Override
  public void deleteVendorById(Long id) {
    vendorRepository.deleteById(id);
  }

  private String getVendorUrl(Long id) {
    return VendorController.BASE_URL + "/" + id;
  }

  private VendorDTO saveAndReturnDTO(Vendor vendor) {
    Vendor savedVendor = vendorRepository.save(vendor);

    VendorDTO returnDto = vendorMapper.vendorToVendorDTO(savedVendor);

    returnDto.setVendorUrl(getVendorUrl(savedVendor.getId()));

    return returnDto;
  }
}
