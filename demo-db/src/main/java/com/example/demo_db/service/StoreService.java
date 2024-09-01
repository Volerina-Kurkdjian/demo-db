package com.example.demo_db.service;


import com.example.demo_db.entity.Store;
import com.example.demo_db.entity.StoreInventory;
import com.example.demo_db.exception.ResourceNotFoundException;
import com.example.demo_db.repository.StoreInventoryRepository;
import com.example.demo_db.repository.StoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class StoreService {

    private final StoreInventoryRepository inventoryRepository;
    private final StoreRepository storeRepository;


    public Store createStore(Store store){
        //setting the id of the store
       // store.setStoreId(String.valueOf(UUID.randomUUID()));

        return storeRepository.save(store);
    }

    public Store updateStore(Store store){

        if(storeRepository.existsById(store.getStoreId())){
            Store newStore=new Store();
            newStore.setName(store.getName());
            newStore.setContactInformation(store.getContactInformation());
            //copying List from request to List saved in db
            Collections.copy(store.getStoreInventory(),newStore.getStoreInventory());

            return storeRepository.save(store);
        }
        else{
            throw new ResourceNotFoundException("Store not found");
        }
    }

    public List<StoreInventory> getInventoryList(String storeId){

        if(storeRepository.existsById(storeId)){
            Store bdStore=  storeRepository.findById(storeId).orElseThrow(()-> new ResourceNotFoundException("The store doesn't exist"));
            return bdStore.getStoreInventory();
        }
        else{
            throw new ResourceNotFoundException("Store not found");
        }
    }

    public Store getStore(String storeIds){
        if(storeRepository.existsById(storeIds)){
            return storeRepository.findById(storeIds).orElseThrow(()-> new ResourceNotFoundException("The store doesn't exist"));
        }
        else{
            throw new ResourceNotFoundException("Store not found");
        }
    }

    public List<StoreInventory> getStoreInventory(String inventoryId) throws ResourceNotFoundException {
        if (storeRepository.existsById(inventoryId)) {
            return inventoryRepository.findAllByInventoryId(inventoryId);
        } else {
            throw new ResourceNotFoundException("Store not found");
        }
    }


}




