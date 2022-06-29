package com.category.service.web.category;

import java.time.LocalDateTime;

import com.category.service.category.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateCategoryResponse {
  private final long id;
  private final String name;
  private final Integer sort;
  private final LocalDateTime createdDateTime;

  @Builder
  CreateCategoryResponse(long id,
                         String name,
                         int sort,
                         LocalDateTime createdDateTime) {
    this.id = id;
    this.name = name;
    this.sort = sort;
    this.createdDateTime = createdDateTime;
  }

  /**
   * 카테고리 생성 정보 객체를 반환합니다.
   */
  static CreateCategoryResponse of(Category category) {
    return CreateCategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .sort(category.getSort())
        .createdDateTime(category.getCreatedDateTime())
        .build();
  }
}
