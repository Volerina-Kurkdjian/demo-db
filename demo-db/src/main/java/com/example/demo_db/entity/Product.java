package com.example.demo_db.entity;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity

//owning side of the relationship
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable=false)
    private Long price;

    @ManyToOne
    private Category category;

    @ManyToOne
    private StoreInventory storeInventory;



}
