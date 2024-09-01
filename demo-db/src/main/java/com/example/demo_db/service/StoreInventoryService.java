package com.example.demo_db.service;


import com.example.demo_db.entity.Category;
import com.example.demo_db.entity.Product;
import com.example.demo_db.entity.Store;
import com.example.demo_db.entity.StoreInventory;
import com.example.demo_db.exception.ResourceNotFoundException;
import com.example.demo_db.repository.StoreInventoryRepository;
import com.example.demo_db.repository.StoreRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@Service
@Data
public class StoreInventoryService {

    private final StoreInventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;


    public StoreInventory createInventory(StoreInventory storeInventory){
//        if(storeInventory.getStore()==null){
//            return inventoryRepository.save(storeInventory);
//        }
//        Store store=storeInventory.getStore();
//        // store.setStoreId(String.valueOf(UUID.randomUUID()));
//        storeRepository.save(store);
        return inventoryRepository.save(storeInventory);
    }

    public StoreInventory registerInventory(String storeInventoryId, String storeId){

        StoreInventory storeInventory=inventoryRepository.findById(storeInventoryId).orElseThrow(()->new ResourceNotFoundException("The inventory does not exist"));
        Store store=storeRepository.findById(storeId).orElseThrow(()->new ResourceNotFoundException("The store does not exist"));
       // storeInventory.setStore(store);

          store.getStoreInventory().add(storeInventory);
       // storeInventory.setStore(store);  -------> daca fac asta da eroarea respectiva, inlantuire
        store.getStoreInventory();
        storeInventory.getStore();

     /////////   storeInventory.setStore(store);
        return   inventoryRepository.save(storeInventory);
       //  inventoryRepository.findById( storeInventoryId).orElseThrow(()->new ResourceNotFoundException("The inventory does not exist"));
    }
    public StoreInventory registerInventoryFinal(String storeInventoryId, String storeId) {
        // Fetch the StoreInventory object from the repository
        StoreInventory storeInventory = inventoryRepository.findById(storeInventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("The inventory does not exist"));

        // Fetch the Store object from the repository
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new ResourceNotFoundException("The store does not exist"));

        // Set the Store in the StoreInventory
      //  storeInventory.setStore(store);

        // Add the StoreInventory to the Store's list
        store.getStoreInventory().add(storeInventory);

        // Save and return the updated StoreInventory object
        return inventoryRepository.save(storeInventory);
    }

}
