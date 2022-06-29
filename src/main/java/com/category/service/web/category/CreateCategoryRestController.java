package com.category.service.web.category;

import javax.validation.Valid;

import com.category.service.category.CreateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카테고리 생성 요청을 처리합니다.
 */
@RequiredArgsConstructor
@RestController
public class CreateCategoryRestController {

  private final CreateCategoryUseCase createCategoryUseCase;

  @PostMapping("/api/categories")
  public CreateCategoryResponse create(@RequestBody @Valid CreateCategoryRequest request) {
    return CreateCategoryResponse.of(
        createCategoryUseCase.create(request.toCommand()));
  }
}
