package com.example.demo_db.service;


import com.example.demo_db.entity.Category;
import com.example.demo_db.entity.Product;
import com.example.demo_db.entity.StoreInventory;
import com.example.demo_db.exception.ResourceNotFoundException;
import com.example.demo_db.repository.CategoryRepository;
import com.example.demo_db.repository.ProductRepository;
import com.example.demo_db.repository.StoreInventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final StoreInventoryRepository inventoryRepository;


    public Product createProduct(Product product){

        if(product.getCategory()!=null || product.getStoreInventory()!=null){
            //adding product to category when saved
          if(product.getCategory()!=null){

              Category category=  product.getCategory();
              category.setCategoryId(String.valueOf(UUID.randomUUID()));
              categoryRepository.save(category);
          }
            //adding product to inventory when saved
          if(product.getStoreInventory()!=null){

              StoreInventory inventory=product.getStoreInventory();
              List<Product> list=new ArrayList<>();
              list.add(product);
              Collections.copy(list, inventory.getProducts());
              inventory.setProducts(list);
              inventoryRepository.save(inventory);
          }

          return productRepository.save(product);
        }

        return productRepository.save(product);
    }


    public Product registerProduct(String productId,String categoryId){

        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("The product does not exist"));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("The category does not exist"));
        category.addProduct(product);
        categoryRepository.save(category);
        return  productRepository.save(product);
    }


    public Product unregisterProduct(String productId,String categoryId){
        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("The product does not exist"));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("The category does not exist"));
        category.removeProduct(product);
        categoryRepository.save(category);
        return  productRepository.save(product);
    }

    public Product registerProductInInventory(String productId,String inventoryId){

        Product product=productRepository.findById(productId).orElseThrow(()->new ResourceNotFoundException("The product does not exist"));
        StoreInventory storeInventory=inventoryRepository.findById(inventoryId).orElseThrow(()->new ResourceNotFoundException("The category does not exist"));
        storeInventory.addProduct(product);
        inventoryRepository.save(storeInventory);
        return  productRepository.save(product);
    }
}
