package com.retail.store.service;

import com.retail.store.Dto.SalesItemDto;
import com.retail.store.Dto.SalesOrderDto;
import com.retail.store.entity.Customer;
import com.retail.store.entity.Products;
import com.retail.store.entity.Sales;
import com.retail.store.repository.CustomerRepository;
import com.retail.store.repository.ProductsRepository;
import com.retail.store.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;
    private final CustomerRepository customerRepository;
    private final ProductsRepository productsRepository;

    public SalesService(SalesRepository salesRepository,
                        CustomerRepository customerRepository,
                        ProductsRepository productsRepository) {
        this.salesRepository = salesRepository;
        this.customerRepository = customerRepository;
        this.productsRepository = productsRepository;
    }

    public List<Sales> saveSalesOrder(SalesOrderDto orderDto) {
        // Validate customer
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<Sales> savedItems = new ArrayList<>();

        for (SalesItemDto itemDto : orderDto.getItems()) {

            // Validate product
            Products product = productsRepository.findById(itemDto.getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            // Handle nulls safely
            BigDecimal price = itemDto.getPrice() != null ? itemDto.getPrice() : BigDecimal.ZERO;
            int quantity = itemDto.getStock() != null ? itemDto.getStock() : 0;

            // Calculate line total safely with BigDecimal
            BigDecimal lineTotal = price.multiply(BigDecimal.valueOf(quantity));

            Integer stock = null;
            Sales sale = Sales.builder()
                    .product(product)
                    .price(price)
                    .stock(stock)
                    .lineTotal(lineTotal)
                    .build();

            savedItems.add(salesRepository.save(sale));
        }

        return savedItems;
    }
}
