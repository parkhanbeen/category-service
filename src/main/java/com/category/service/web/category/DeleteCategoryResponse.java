package com.category.service.web.category;

import lombok.Getter;

@Getter
public class DeleteCategoryResponse {
  private final long id;

  DeleteCategoryResponse(long id) {
    this.id = id;
  }

  /**
   * 카테고리 삭제 정보 객체를 반환합니다.
   */
  static DeleteCategoryResponse of(long categoryId) {
    return new DeleteCategoryResponse(categoryId);
  }
}
