package com.retail.store.service;

import com.retail.store.Dto.DashboardDto;
import com.retail.store.entity.Dashboard;
import com.retail.store.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DashboardService {
    private final CustomerRepository customerRepo;
    private final ProductsRepository productRepo;
    private final PurchaseRepository purchaseRepo;
    private final SalesRepository salesRepo;

    public DashboardService(CustomerRepository customerRepo,
                            ProductsRepository productRepo,
                            PurchaseRepository purchaseRepo,
                            SalesRepository salesRepo) {
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.purchaseRepo = purchaseRepo;
        this.salesRepo = salesRepo;
    }

    public DashboardDto getDashboard() {

        DashboardDto dto = new DashboardDto();

        dto.setTotalCustomers(customerRepo.count());
        dto.setTotalProducts(productRepo.count());

        // Purchase and Sales totals
        Double purchaseAmount = purchaseRepo.sumTotalPurchase();
        Double salesAmount = salesRepo.sumTotalSales();

        dto.setTotalPurchaseAmount(purchaseAmount != null ? purchaseAmount : 0);
        dto.setTotalSalesAmount(salesAmount != null ? salesAmount : 0);

        dto.setTotalProfit(dto.getTotalSalesAmount() - dto.getTotalPurchaseAmount());

        // Today sales
        dto.setTodaySalesAmount(salesRepo.sumTodaySales(LocalDate.now()));
        dto.setTodaySalesCount(salesRepo.countTodaySales(LocalDate.now()));

        return dto;
    }

}
