package com.category.service.category.entity;

import java.util.List;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {

  private final JPAQueryFactory queryFactory;
  private final QCategory category = QCategory.category;
  private final QCategory childCategory = new QCategory("childCategory");

  @Override
  public List<Category> findCategories() {
    return getCategoryQuery()
        .where(
            category.parent.isNull()
        )
        .orderBy(
            category.sort.asc()
        )
        .fetch();
  }

  @Override
  public List<Category> findCategoriesById(Long id) {
    return getCategoryQuery()
        .where(
            eqCategoryId(id)
        )
        .orderBy(
            category.sort.asc()
        )
        .fetch();
  }

  private JPAQuery<Category> getCategoryQuery() {
    return queryFactory
        .selectFrom(category)
        .distinct()
        .leftJoin(category.children, childCategory)
        .fetchJoin();
  }

  /**
   * 카테고리 식별자 조회.
   */
  private BooleanExpression eqCategoryId(Long id) {
    return id != null ? category.id.eq(id) : null;
  }


}
