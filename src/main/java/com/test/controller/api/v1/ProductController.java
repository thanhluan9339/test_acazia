package com.test.controller.api.v1;

import com.test.common.enums.ResponseCodeEnum;
import com.test.controller.api.request.CreateProductRequest;
import com.test.controller.api.request.UpdateProductRequest;
import com.test.controller.response.ResponseBodyDto;
import com.test.dto.ProductDto;
import com.test.dto.TestDto;
import com.test.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
public class ProductController {
    @Autowired
    ProductService productService;

    //Get list
    @GetMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> getAllByCondition(
            @RequestParam(name = "name", required = false) String name ,
            @RequestParam(name = "category_tag", required = false) String categoryTag ,
            Pageable pageable) {

        Page<ProductDto> page = productService.getAllByCondition(name , categoryTag , pageable);

        ResponseBodyDto<ProductDto> res = new ResponseBodyDto<ProductDto>(pageable , page , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //Create
    @PostMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> create(
            @Valid @RequestBody CreateProductRequest request) {
        ProductDto productDto = productService.create(request);
        ResponseBodyDto<ProductDto> res = new ResponseBodyDto<ProductDto>(productDto ,
                ResponseCodeEnum.R_201 , "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //update
    @PatchMapping(value = "/product/{product_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> update(
            @PathVariable(name = "product_name") String productName ,
            @Valid @RequestBody UpdateProductRequest request) {
        ProductDto productDto = productService.update(productName , request);
        ResponseBodyDto<ProductDto> res = new ResponseBodyDto<ProductDto>(productDto , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // delete
    @DeleteMapping(value = "/product/{product_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<String>> delete(@PathVariable(name = "product_name") String productName) {
        Boolean checkDeleted = productService.delete(productName);
        if (checkDeleted == null || !checkDeleted) {
            ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_500 , "Lỗi dữ liệu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        } else {
            ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_200 , "Xóa category thành công");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    //detail
    @GetMapping(value = "/product/{product_name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<ProductDto>> detail(
            @PathVariable(name = "product_name") String productName) {
        ProductDto productDto = productService.detail(productName);
        ResponseBodyDto<ProductDto> res = new ResponseBodyDto<ProductDto>(productDto , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //viết 1 API đáp ứng yêu cầu sau:
    //nhận vào 1 String, trả về danh sách các product có thuộc tính categoryTag và categoryName sao cho categoryName
    // gần giống với input (like %) và được sắp xếp theo: `price` giảm dần, trường hơp `price` bằng nhau thì sắp xếp
    // theo `name` tăng dần (không phân biệt hoa thường).

    //Get detail
    @GetMapping(value = "/product-test", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<TestDto>> getAllByConditionTest(
            @RequestParam(name = "category_name", required = false) String categoryName ,
            Pageable pageable) {

        Page<TestDto> page = productService.getAllByConditionTest(categoryName , pageable);

        ResponseBodyDto<TestDto> res = new ResponseBodyDto<TestDto>(pageable , page , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
