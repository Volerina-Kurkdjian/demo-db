package com.example.demo_db.controller;



import com.example.demo_db.entity.Category;
import com.example.demo_db.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<Category> createCategory(@RequestBody Category productCategory){
        Category categoryDto=  categoryService.createCategory(productCategory);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name="id") String id){
        Category categoryDto=categoryService.getCategoryById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDto);
    }


}
