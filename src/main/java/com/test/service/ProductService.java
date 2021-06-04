package com.test.service;

import com.test.controller.api.request.CreateProductRequest;
import com.test.controller.api.request.UpdateProductRequest;
import com.test.dto.CategoryDto;
import com.test.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductDto> getAllByCondition(String name , String categoryTag , Pageable pageable);

    ProductDto create(CreateProductRequest request);

    ProductDto update(String productId , UpdateProductRequest request);

    Boolean delete(String productId);

    Page<ProductDto> getAllByConditionTest(String categoryName , Pageable pageable);

    ProductDto detail(String productId);
}
