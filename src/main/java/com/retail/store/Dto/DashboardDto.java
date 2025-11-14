package com.retail.store.Dto;

import lombok.Data;

@Data

public class DashboardDto {
    private long totalCustomers;
    private long totalProducts;
    private double totalSalesAmount;
    private double totalPurchaseAmount;
    private double totalProfit;
    private long todaySalesCount;
    private double todaySalesAmount;
    public double salesToday;
    public long lowStockCount;
    public long pendingOrders;
    public double totalRevenue;
    public long newCustomers;
    public long totalOrders;
    public int customerSatisfaction;

}
