package com.category.service.category;

import com.category.service.category.entity.Category;
import lombok.Builder;

public class CreateCategoryCommand {
  private final String name;
  private final Integer sort;

  @Builder
  CreateCategoryCommand(String name,
                        int sort) {
    this.name = name;
    this.sort = sort;
  }

  /**
   * 카테고리 엔티티를 반환합니다.
   */
  public Category toEntity() {
    return Category.builder()
        .name(name)
        .sort(sort)
        .build();
  }
}
