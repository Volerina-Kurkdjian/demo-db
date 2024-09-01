package com.example.demo_db.controller;


import com.example.demo_db.entity.StoreInventory;
import com.example.demo_db.service.StoreInventoryService;
import com.example.demo_db.service.StoreService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/inventory")
public class InventoryController {


    private final StoreInventoryService inventoryService;

    @PostMapping("/create")
    public ResponseEntity<StoreInventory> create(@RequestBody StoreInventory storeInventory){
        inventoryService.createInventory(storeInventory);
        return ResponseEntity.status(HttpStatus.CREATED).body( inventoryService.createInventory(storeInventory));
    }

    @PostMapping("/addInventory/{inventoryId}/{storeId}")
    public ResponseEntity<StoreInventory> addInventoryToStore(@PathVariable String inventoryId,@PathVariable String storeId){
        return ResponseEntity.status(HttpStatus.OK).body(inventoryService.registerInventoryFinal(inventoryId,storeId));
    }

}
