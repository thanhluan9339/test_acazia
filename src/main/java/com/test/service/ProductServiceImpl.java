package com.test.service;

import com.test.common.error.BadRequestException;
import com.test.common.error.NotFoundException;
import com.test.common.mapper.CommonMapper;
import com.test.controller.api.request.CreateProductRequest;
import com.test.controller.api.request.UpdateProductRequest;
import com.test.dto.ProductDto;
import com.test.model.Product;
import com.test.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<ProductDto> getAllByCondition(String name , String categoryTag , Pageable pageable) {
        Page<Product> page = productRepository.getAllByCondition(name , categoryTag , pageable);
        return CommonMapper.toPage(page , ProductDto.class , pageable);
    }

    @Override
    public ProductDto create(CreateProductRequest request) {
        if (productRepository.existsByName(request.getName())) {
            throw new BadRequestException("Product đã tồn tại");
        }
        Product product = new Product();
        CommonMapper.copyPropertiesIgnoreNull(request , product);
        productRepository.save(product);
        return CommonMapper.map(product , ProductDto.class);
    }

    @Override
    public ProductDto update(String productId , UpdateProductRequest request) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product không tồn tại");
        }
        Product product = optional.get();
        CommonMapper.copyPropertiesIgnoreNull(request , product);
        productRepository.save(product);
        return CommonMapper.map(product , ProductDto.class);
    }

    @Override
    public Boolean delete(String productId) {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product không tồn tại");
        }
        productRepository.deleteById(productId);
        return true;
    }

    @Override
    public Page<ProductDto> getAllByConditionTest(String categoryName , Pageable pageable) {
        Page<Product> page = productRepository.getAllByConditionTest(categoryName , pageable);
        return CommonMapper.toPage(page , ProductDto.class , pageable);
    }

    @Override
    public ProductDto detail(String productId) {
        Optional<Product> optional = productRepository.findById(productId);
        if (optional.isEmpty()) {
            throw new NotFoundException("Product không tồn tại");
        }
        Product product = optional.get();
        return CommonMapper.map(product , ProductDto.class);
    }
}
