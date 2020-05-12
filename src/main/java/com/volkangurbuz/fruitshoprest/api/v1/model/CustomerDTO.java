package com.volkangurbuz.fruitshoprest.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

  // swagger note
  @ApiModelProperty(value = "this is first name", required = true)
  private String firstName;

  @ApiModelProperty(value = "Last Name", required = true)
  private String lastName;

  @JsonProperty("customer_url")
  private String customerUrl;
}
