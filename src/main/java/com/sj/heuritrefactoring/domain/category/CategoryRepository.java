package com.sj.heuritrefactoring.domain.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByCategoryId(Long CategoryId);
}
