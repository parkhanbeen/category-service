package com.category.service.category;

import com.category.service.category.entity.Category;
import lombok.Builder;
import lombok.Getter;

public class CreateCategoryCommand {
  @Getter
  private final Long parentId;
  private final String name;
  private final Integer sort;

  @Builder
  CreateCategoryCommand(Long parentId,
                        String name,
                        int sort) {
    this.parentId = parentId;
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

  /**
   * 최상위 카테고리일 경우 true 를 반환합니다.
   */
  boolean isRootCategory() {
    return parentId == null;
  }
}
