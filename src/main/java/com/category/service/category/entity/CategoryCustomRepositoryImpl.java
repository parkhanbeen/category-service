package com.category.service.category.entity;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

  private final JPAQueryFactory queryFactory;
  private final QCategory category = QCategory.category;
  private final QCategory childCategory = new QCategory("childCategory");

  @Override
  public List<Category> findCategories() {
    return queryFactory
        .selectFrom(category)
        .distinct()
        .leftJoin(category.children, childCategory)
        .fetchJoin()
        .where(
            category.parent.isNull()
        )
        .orderBy(
            category.sort.asc()
        )
        .fetch();
  }
}
