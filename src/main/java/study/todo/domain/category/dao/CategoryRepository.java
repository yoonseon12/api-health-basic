package study.todo.domain.category.dao;

import study.todo.domain.category.domain.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
