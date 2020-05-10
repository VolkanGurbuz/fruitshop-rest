package com.volkangurbuz.fruitshoprest.repositories;

import com.volkangurbuz.fruitshoprest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}
