package kr.co.wizclass.wizhealth.domain.category.dao;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepositoryCustom {
    Optional<Category> findCategoryById(Long categoryId);

    List<Category> findAllByIdIn(List<Long> categoryIds);
}
