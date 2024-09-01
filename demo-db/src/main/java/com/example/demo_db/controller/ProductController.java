package com.example.demo_db.controller;



import com.example.demo_db.entity.Product;
import com.example.demo_db.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product productDto){
        Product productDtoTemporary=productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.OK).body(productDtoTemporary);
    }

    @PostMapping("/addCategory/{productId}/{categoryId}")
    public ResponseEntity<Product> addProductToCategory(@PathVariable String productId,@PathVariable String categoryId){
        return  ResponseEntity.status(HttpStatus.OK).body(productService.registerProduct(productId,categoryId));
    }

    @PostMapping("/removeCategory/{productId}/{categoryId}")
    public ResponseEntity<Product> unregisterProductFromCategory(@PathVariable String productId,@PathVariable String categoryId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.unregisterProduct(productId,categoryId));
    }

    @PostMapping("/addToInventory/{productId}/{inventoryId}")
    public ResponseEntity<Product> registerProductInInventory(@PathVariable String productId,@PathVariable String inventoryId){
        return ResponseEntity.status(HttpStatus.OK).body(productService.registerProductInInventory(productId,inventoryId));
    }



}
