package com.example.demo_db.repository;

import com.example.demo_db.entity.StoreInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StoreInventoryRepository extends JpaRepository<StoreInventory,String> {
    //List<StoreInventory> findAllByStoreId(String inventoryId);

    List<StoreInventory> findAllByInventoryId(String inventoryId);
    // List<StoreInventory> findAllByStoreId(String storeId);
}
