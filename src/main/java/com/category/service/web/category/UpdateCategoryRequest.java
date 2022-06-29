package com.category.service.web.category;

import com.category.service.category.UpdateCategoryCommand;
import lombok.Getter;

@Getter
public class UpdateCategoryRequest {
  private String name;
  private Integer sort;

  private UpdateCategoryRequest() {
  }

  UpdateCategoryCommand toCommand() {
    return UpdateCategoryCommand.builder()
        .name(name)
        .sort(sort)
        .build();
  }
}
