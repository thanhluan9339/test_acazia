package com.test.controller.api.v1;

import com.test.common.enums.ResponseCodeEnum;
import com.test.controller.api.request.CreateCategoryRequest;
import com.test.controller.api.request.UpdateCategoryRequest;
import com.test.controller.response.ResponseBodyDto;
import com.test.dto.CategoryDto;
import com.test.service.CategoryService;
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
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //Get list
    @GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<CategoryDto>> getAllByCondition(
            @RequestParam(name = "name", required = false) String name ,
            @RequestParam(name = "tag", required = false) String tag ,
            Pageable pageable) {

        Page<CategoryDto> page = categoryService.getAllByCondition(name , tag , pageable);

        ResponseBodyDto<CategoryDto> res = new ResponseBodyDto<CategoryDto>(pageable , page , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    //Create
    @PostMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<CategoryDto>> create(
            @Valid @RequestBody CreateCategoryRequest request) {
        CategoryDto categoryDto = categoryService.create(request);
        ResponseBodyDto<CategoryDto> res = new ResponseBodyDto<CategoryDto>(categoryDto ,
                ResponseCodeEnum.R_201 , "CREATED");
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    //update
    @PatchMapping(value = "/category/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<CategoryDto>> update(
            @PathVariable(name = "category_id") String categoryId ,
            @Valid @RequestBody UpdateCategoryRequest request) {
        CategoryDto categoryDto = categoryService.update(categoryId , request);
        ResponseBodyDto<CategoryDto> res = new ResponseBodyDto<CategoryDto>(categoryDto , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    // delete
    @DeleteMapping(value = "/category/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<String>> delete(@PathVariable(name = "category_id") String categoryId) {
        Boolean checkDeleted = categoryService.delete(categoryId);
        if (checkDeleted == null || !checkDeleted) {
            ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_500 , "Lỗi dữ liệu");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
        } else {
            ResponseBodyDto<String> res = new ResponseBodyDto<>(ResponseCodeEnum.R_200 , "Xóa category thành công");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }
    }

    //detail
    @GetMapping(value = "/category/{category_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBodyDto<CategoryDto>> detail(
            @PathVariable(name = "category_id") String categoryId) {
        CategoryDto categoryDto = categoryService.detail(categoryId);
        ResponseBodyDto<CategoryDto> res = new ResponseBodyDto<CategoryDto>(categoryDto , ResponseCodeEnum.R_200 , "OK");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
