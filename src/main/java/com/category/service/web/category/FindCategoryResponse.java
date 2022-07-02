package com.category.service.web.category;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.category.service.category.entity.Category;
import lombok.Getter;

@Getter
public class FindCategoryResponse {

  private final long id;
  private final String name;
  private final Integer sort;
  private final LocalDateTime modifiedDateTime;
  private final LocalDateTime createdDateTime;
  private final List<FindCategoryResponse> children;

  FindCategoryResponse(Category categories) {
    this.id = categories.getId();
    this.name = categories.getName();
    this.sort = categories.getSort();
    this.modifiedDateTime = categories.getModifiedDateTime();
    this.createdDateTime = categories.getCreatedDateTime();
    this.children = FindCategoryResponse.of(categories.getChildren());
  }

  static List<FindCategoryResponse> of(List<Category> categories) {
    return categories.stream()
        .map(FindCategoryResponse::new)
        .collect(Collectors.toList());
  }
}
