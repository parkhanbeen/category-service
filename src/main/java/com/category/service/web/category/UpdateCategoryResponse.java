package com.category.service.web.category;

import java.time.LocalDateTime;

import com.category.service.category.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCategoryResponse {
  private final long id;
  private final String name;
  private final Integer sort;
  private final LocalDateTime modifiedDateTime;

  @Builder
  UpdateCategoryResponse(long id,
                         String name,
                         Integer sort,
                         LocalDateTime modifiedDateTime) {
    this.id = id;
    this.name = name;
    this.sort = sort;
    this.modifiedDateTime = modifiedDateTime;
  }

  static UpdateCategoryResponse of(Category category) {
    return UpdateCategoryResponse.builder()
        .id(category.getId())
        .name(category.getName())
        .sort(category.getSort())
        .modifiedDateTime(category.getModifiedDateTime())
        .build();
  }
}
