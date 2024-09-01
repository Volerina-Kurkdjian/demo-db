package com.example.demo_db.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class StoreInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String inventoryId;

    @ManyToOne
    private Store store;

    @OneToMany(mappedBy ="storeInventory")
    private List<Product> products=new ArrayList<>();

    @Column(nullable = false)
    private Integer quantity;

    public void addItems(int count) {
        this.quantity += count;
    }

    public void removeItems(int count) {
        this.quantity -= count;
    }

    public void addProduct(Product productToAdd){
        products.add(productToAdd);
        productToAdd.setStoreInventory(this);
    }

    public void removeProduct(Product productToRemove){
        products.remove(productToRemove);
        productToRemove.setStoreInventory(null);
    }

    //////////////////////////////////
    public void setStore(Store store) {
        this.store = store;

        // Avoid adding this StoreInventory to the Store's list if it's already present
        if (store != null && !store.getStoreInventory().contains(this)) {
            store.getStoreInventory().add(this);
        }
    }

}
