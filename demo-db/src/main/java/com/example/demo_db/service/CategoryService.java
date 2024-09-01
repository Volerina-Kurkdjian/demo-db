package com.example.demo_db.service;


import com.example.demo_db.entity.Category;
import com.example.demo_db.exception.ResourceNotFoundException;
import com.example.demo_db.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

  public Category createCategory(Category request){

      request.setCategoryId(String.valueOf(UUID.randomUUID()));
      Category productCategoryEntity= categoryRepository.save(request);
      return productCategoryEntity;

  }

  public Category getCategoryById(String id){
      return categoryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("The category with Id does not exists!"));
  }




}
