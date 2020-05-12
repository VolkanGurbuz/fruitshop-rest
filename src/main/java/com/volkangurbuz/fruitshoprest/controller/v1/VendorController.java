package com.volkangurbuz.fruitshoprest.controller.v1;

import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTO;
import com.volkangurbuz.fruitshoprest.api.v1.model.VendorDTOList;
import com.volkangurbuz.fruitshoprest.services.VendorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(VendorController.BASE_URL)
public class VendorController {

  public static final String BASE_URL = "/api/v1/vendors";

  private final VendorService vendorService;

  public VendorController(VendorService vendorService) {
    this.vendorService = vendorService;
  }

  @ApiOperation(
      value = "This will get a list of vendors",
      notes = "there are some notes about the api")
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public VendorDTOList getVendorList() {
    return vendorService.getAllVendors();
  }

  @GetMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO getVendorById(@PathVariable Long id) {
    return vendorService.getVendorById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public VendorDTO createNewVendor(@RequestBody VendorDTO vendorDTO) {
    return vendorService.createNewVendor(vendorDTO);
  }

  @PutMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @PatchMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
    return vendorService.saveVendorByDTO(id, vendorDTO);
  }

  @DeleteMapping({"/{id}"})
  @ResponseStatus(HttpStatus.OK)
  public void deleteVendor(@PathVariable Long id) {
    vendorService.deleteVendorById(id);
  }
}
