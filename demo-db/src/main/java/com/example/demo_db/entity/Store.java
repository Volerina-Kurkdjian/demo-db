package com.example.demo_db.entity;

import io.micrometer.common.lang.NonNull;
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
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String storeId;

    @NonNull
    @Column(length = 256)
     String name;
    @Column(length = 256)
     String contactInformation;

    @OneToMany(mappedBy = "store")
    private List<StoreInventory> storeInventory=new ArrayList<>();

    public void addInventory(StoreInventory inventory){
        storeInventory.add(0,inventory);
        inventory.setStore(this);
    }

    public void removeInventory(StoreInventory inventory){
        storeInventory.remove(inventory);
        inventory.setStore(null);
    }
}
