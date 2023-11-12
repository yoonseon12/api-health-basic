package study.todo.domain.category.dao;

import study.todo.domain.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    Optional<Category> findCategoryById(Long categoryId);

    List<Category> findAllByIdIn(List<Long> categoryIds);
}
