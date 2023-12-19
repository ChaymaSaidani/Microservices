package com.example.inventoryservice;

import com.example.inventoryservice.entity.Inventory;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullInventory", types = Inventory.class)
public interface ProductProjection {
    Long getId();
    String getName();
    double getPrice();
}
