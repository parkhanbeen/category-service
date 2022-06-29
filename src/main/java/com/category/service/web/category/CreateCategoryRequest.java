package com.category.service.web.category;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.category.service.category.CreateCategoryCommand;
import lombok.Getter;

@Getter
public class CreateCategoryRequest {

  @NotBlank(message = "카테고리 명은 필수 입력입니다.")
  private String name;

  @Min(value = 1, message = "최소 1이상 입력되어야 합니다.")
  private Integer sort;

  private CreateCategoryRequest() {
  }

  /**
   * 카테고리 생성 커맨드 객체를 반환합니다.
   */
  CreateCategoryCommand toCommand() {
    return CreateCategoryCommand.builder()
        .name(name)
        .sort(sort)
        .build();
  }
}
