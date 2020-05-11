package com.volkangurbuz.fruitshoprest.bootstrap;

import com.volkangurbuz.fruitshoprest.domain.Category;
import com.volkangurbuz.fruitshoprest.domain.Customer;
import com.volkangurbuz.fruitshoprest.repositories.CategoryRepository;
import com.volkangurbuz.fruitshoprest.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
// use spring events before with commandLineRunner
public class Bootstrap implements CommandLineRunner {

  private CategoryRepository categoryRepository;
  private CustomerRepository customerRepository;

  public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
    this.categoryRepository = categoryRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void run(String... args) throws Exception {

    loadCustomers();
    loadCategories();
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
}
