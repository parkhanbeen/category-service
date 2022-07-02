package com.category.service.web.category;

import java.util.List;

import com.category.service.category.FindCategoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 카테고리 조회 요청을 처리합니다.
 */
@RequiredArgsConstructor
@RestController
public class FindCategoryRestController {

  private final FindCategoryUseCase findCategoryUseCase;

  @GetMapping("/api/categories")
  public List<FindCategoryResponse> findAll() {
    return FindCategoryResponse.of(findCategoryUseCase.findCategories());
  }

  @GetMapping("/api/categories/{categoryId}")
  public List<FindCategoryResponse> findById(@PathVariable("categoryId") Long id) {
    return FindCategoryResponse.of(findCategoryUseCase.findCategoriesById(id));
  }
}
