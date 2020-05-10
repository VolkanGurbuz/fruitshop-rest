package com.volkangurbuz.fruitshoprest.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryListDTO {

  List<CategoryDTO> categories;
}
