package com.category.service.category;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateCategoryCommand {
  private final String name;
  private final Integer sort;

  @Builder
  private UpdateCategoryCommand(String name, int sort) {
    this.name = name;
    this.sort = sort;
  }
}
