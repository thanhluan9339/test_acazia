package com.test.service;

import com.test.common.error.BadRequestException;
import com.test.common.error.NotFoundException;
import com.test.common.mapper.CommonMapper;
import com.test.controller.api.request.CreateCategoryRequest;
import com.test.controller.api.request.UpdateCategoryRequest;
import com.test.dto.CategoryDto;
import com.test.model.Category;
import com.test.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Page<CategoryDto> getAllByCondition(String name , String tag , Pageable pageable) {
        Page<Category> page = categoryRepository.getAllByCondition(name , tag , pageable);
        return CommonMapper.toPage(page , CategoryDto.class , pageable);
    }

    @Override
    public CategoryDto create(CreateCategoryRequest request) {
        if (categoryRepository.existsByTag(request.getTag())) {
            throw new BadRequestException("Category đã tồn tại");
        }
        Category category = new Category();
        CommonMapper.copyPropertiesIgnoreNull(request , category);
        categoryRepository.save(category);
        return CommonMapper.map(category , CategoryDto.class);
    }

    @Override
    public CategoryDto update(String categoryId , UpdateCategoryRequest request) {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (!optional.isPresent()) {
            throw new NotFoundException("Category không tồn tại");
        }
        Category category = optional.get();
        CommonMapper.copyPropertiesIgnoreNull(request , category);
        categoryRepository.save(category);
        return CommonMapper.map(category , CategoryDto.class);
    }

    @Override
    public Boolean delete(String categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new NotFoundException("Category không tồn tại");
        }
        categoryRepository.deleteById(categoryId);
        return true;
    }

    @Override
    public CategoryDto detail(String categoryId) {
        Optional<Category> optional = categoryRepository.findById(categoryId);
        if (!optional.isPresent()) {
            throw new NotFoundException("Category không tồn tại");
        }
        Category category = optional.get();
        return CommonMapper.map(category , CategoryDto.class);

    }
}
