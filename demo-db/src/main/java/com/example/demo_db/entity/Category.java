package com.example.demo_db.entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Category {

    @Id
    @Column(name = "category_id")
    private String categoryId;

    @NonNull
    @Column
    private String name;

    //@OneToMany(mappedBy = "category", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @OneToMany(mappedBy = "category")
    List<Product> product=new ArrayList<>();


    public void addProduct(Product productToAdd){
        product.add(productToAdd);
        productToAdd.setCategory(this);
    }

    public void removeProduct(Product productToRemove){
        product.remove(productToRemove);
        productToRemove.setCategory(null);
    }

}
