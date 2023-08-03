package kr.co.wizclass.wizhealth.domain.category.dao;

import kr.co.wizclass.wizhealth.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>, CategoryRepositoryCustom {

}
