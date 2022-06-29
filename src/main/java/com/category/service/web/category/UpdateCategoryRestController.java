package com.category.service.web.category;

import javax.validation.Valid;

import com.category.service.category.UpdateCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카테고리 수정 요청을 처리합니다.
 */
@RequiredArgsConstructor
@RestController
public class UpdateCategoryRestController {

  private final UpdateCategoryUseCase updateCategoryUseCase;

  @PutMapping("/api/categories/{categoryId}")
  public UpdateCategoryResponse update(@PathVariable(name = "categoryId") long categoryId,
                                       @RequestBody @Valid UpdateCategoryRequest request) {
    return UpdateCategoryResponse.of(
        updateCategoryUseCase.update(categoryId, request.toCommand()));
  }
}
