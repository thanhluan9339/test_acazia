package com.test.controller.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CreateProductRequest {
    @NotNull(message = "name can not be null")
    @Length(max = 32, message = "name must be less than 32 digits")
    private String name;

    @NotNull(message = "categoryTag can not be null")
    @Length(max = 32, message = "categoryTag must be less than 32 digits")
    private String categoryTag;

    @NotNull(message = "price can not be null")
    private Double price = Double.NaN;
}
