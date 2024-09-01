package com.example.demo_db.controller;


import com.example.demo_db.entity.Store;
import com.example.demo_db.entity.StoreInventory;
import com.example.demo_db.service.StoreInventoryService;
import com.example.demo_db.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;


    @GetMapping("/{storeId}")
    public ResponseEntity<Store> getStore(@PathVariable(name="storeId")String storeId){
        Store store=  storeService.getStore(storeId);
        return  ResponseEntity.status(HttpStatus.FOUND).body(store);
    }

    @PostMapping("/create")
    public ResponseEntity<Store> createStore(@RequestBody Store store){
       Store finalStore= storeService.createStore(store);
        return  ResponseEntity.status(HttpStatus.FOUND).body(finalStore);
    }

    @GetMapping("/getInventory/{storeId}")
    public ResponseEntity<List<StoreInventory>> getInventoryList(@PathVariable(name="storeId")String storeId){
        Store store=  storeService.getStore(storeId);
      return ResponseEntity.status(HttpStatus.OK).body( store.getStoreInventory());
    }

    @GetMapping("/getInventories/{storeId}")
    public ResponseEntity<List<StoreInventory>> getInventorysList(@PathVariable(name="storeId")String storeId){
        List<StoreInventory> list= new ArrayList<>();
            Collections.copy(list, storeService.getInventoryList(storeId));
        return ResponseEntity.status(HttpStatus.OK).body( list);
    }
}
