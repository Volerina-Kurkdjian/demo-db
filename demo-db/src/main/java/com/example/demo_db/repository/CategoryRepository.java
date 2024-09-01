package com.example.demo_db.repository;

import com.example.demo_db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,String> {

    Category getByName(String categoryName);

    void deleteByName(String productCategoryName);
}
