package com.volkangurbuz.fruitshoprest.bootstrap;

import com.volkangurbuz.fruitshoprest.domain.Category;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.domain.Vendor;
import com.volkangurbuz.fruitshoprest.repositories.CategoryRepository;
import com.volkangurbuz.fruitshoprest.repositories.CustomerRepository;
import com.volkangurbuz.fruitshoprest.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// use spring events before with commandLineRunner
public class Bootstrap implements CommandLineRunner {

  private final CategoryRepository categoryRepository;
  private final CustomerRepository customerRepository;
  private final VendorRepository vendorRepository;

  public Bootstrap(
      CategoryRepository categoryRespository,
      CustomerRepository customerRepository,
      VendorRepository vendorRepository) {
    this.categoryRepository = categoryRespository;
    this.customerRepository = customerRepository;
    this.vendorRepository = vendorRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    loadCustomers();
    loadCategories();
    loadVendors();
  }

  public void loadCustomers() {
    // given
    Customer customer = new Customer();
    customer.setId(1L);
    customer.setFirstName("Volkan");
    customer.setLastName("gurbuz");
    customerRepository.save(customer);

    Customer customer2 = new Customer();
    customer2.setId(2L);
    customer2.setFirstName("onur");
    customer2.setLastName("hizlan");
    customerRepository.save(customer2);

    System.out.println("date loaded - " + customerRepository.count());
  }

  public void loadCategories() {
    Category fruits = new Category();
    fruits.setName("Fruits");

    Category dried = new Category();
    dried.setName("Dried");

    Category fresh = new Category();
    fresh.setName("Fresh");

    Category exotic = new Category();
    exotic.setName("Exotic");

    Category nuts = new Category();
    nuts.setName("Nuts");
    categoryRepository.save(fruits);
    categoryRepository.save(dried);
    categoryRepository.save(fresh);
    categoryRepository.save(exotic);
    categoryRepository.save(nuts);
    System.out.println("date loaded - " + categoryRepository.count());
  }

  private void loadVendors() {
    Vendor vendor1 = new Vendor();
    vendor1.setName("Vendor 1");
    vendorRepository.save(vendor1);

    Vendor vendor2 = new Vendor();
    vendor2.setName("Vendor 2");
    vendorRepository.save(vendor2);
  }
}
