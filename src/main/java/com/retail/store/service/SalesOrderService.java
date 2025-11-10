package com.retail.store.service;

import com.retail.store.Dto.SalesOrderDto;
import com.retail.store.entity.Customer;
import com.retail.store.entity.Sales;
import com.retail.store.entity.SalesOrder;
import com.retail.store.repository.CustomerRepository;
import com.retail.store.repository.SalesOrderRepository;
import com.retail.store.repository.SalesRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@Service
@RequiredArgsConstructor
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final SalesRepository salesRepository;

    public List<SalesOrderDto> getAllOrders() {
        return salesOrderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SalesOrderDto getOrderById(Long id) {
        return salesOrderRepository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("SalesOrder not found with id: " + id));
    }

    public SalesOrderDto createOrder(SalesOrderDto dto) {
        Customer customer = customerRepository.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        SalesOrder order = SalesOrder.builder()
                .customer(customer)
                .build();

        if (dto.getSalesItemIds() != null) {
            dto.getSalesItemIds().forEach(saleId -> {
                Sales sale = salesRepository.findById(saleId)
                        .orElseThrow(() -> new RuntimeException("Sale not found with id: " + saleId));
                order.addSale(sale);
            });
        }

        SalesOrder savedOrder = salesOrderRepository.save(order);
        return convertToDTO(savedOrder);
    }

    public void deleteOrder(Long id) {
        if (!salesOrderRepository.existsById(id)) {
            throw new RuntimeException("SalesOrder not found with id: " + id);
        }
        salesOrderRepository.deleteById(id);
    }

    private SalesOrderDto convertToDTO(SalesOrder order) {
        return SalesOrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .salesItemIds(order.getSalesItems()
                        .stream()
                        .map(Sales::getId)
                        .collect(Collectors.toList()))
                .createdAt(order.getCreatedAt())
                .build();
    }
}
