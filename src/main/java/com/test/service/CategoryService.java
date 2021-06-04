package com.test.service;

import com.test.controller.api.request.CreateCategoryRequest;
import com.test.controller.api.request.UpdateCategoryRequest;
import com.test.dto.CategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryDto> getAllByCondition(String name , String tag , Pageable pageable);

    CategoryDto create(CreateCategoryRequest request);

    CategoryDto update(String categoryId , UpdateCategoryRequest request);

    Boolean delete(String categoryId);

    CategoryDto detail(String categoryId);
}
