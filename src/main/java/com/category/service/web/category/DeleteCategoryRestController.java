package com.category.service.web.category;

import com.category.service.category.DeleteCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카테고리 삭제 요청을 처리합니다.
 */
@RequiredArgsConstructor
@RestController
public class DeleteCategoryRestController {

  private final DeleteCategoryUseCase deleteCategoryUseCase;

  @DeleteMapping("/api/categories/{categoryId}")
  public DeleteCategoryResponse delete(@PathVariable("categoryId") long categoryId) {
    return DeleteCategoryResponse.of(deleteCategoryUseCase.delete(categoryId));
  }
}
