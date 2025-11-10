package com.retail.store.controller;

import com.retail.store.Dto.SalesOrderDto;
import com.retail.store.entity.SalesOrder;
import com.retail.store.service.SalesOrderService;
import com.retail.store.service.SalesService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter


@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesService) {
        this.salesOrderService = salesService;
    }

    // ----------------------
    // Create a new Sales Order with multiple items
    // ----------------------
    @PostMapping
    public ResponseEntity<SalesOrderDto> createSalesOrder(
            @Valid @RequestBody SalesOrderDto orderDto) {
        SalesOrderDto savedOrder = salesOrderService.createOrder(orderDto);
        return ResponseEntity.ok(savedOrder);
    }

    // ----------------------
    // Get all Sales Orders
    // ----------------------
    @GetMapping
    public ResponseEntity<List<SalesOrderDto>> getAllSalesOrders() {
        List<SalesOrderDto> orders = salesOrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // ----------------------
    // Get Sales Order by ID
    // ----------------------
    @GetMapping("/{id}")
    public ResponseEntity<SalesOrderDto> getSalesOrderById(@PathVariable Long id) {
        SalesOrderDto order = salesOrderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    // ----------------------
    // Delete Sales Order
    // ----------------------
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSalesOrder(@PathVariable Long id) {
        salesOrderService.deleteOrder(id);
        return ResponseEntity.ok("Sales order deleted successfully with id: " + id);
    }
}
