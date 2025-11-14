package com.retail.store.controller;

import com.retail.store.Dto.DashboardDto;
import com.retail.store.entity.Dashboard;
import com.retail.store.service.DashboardService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {
    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    @GetMapping
    public DashboardDto getDashboard() {
        return service.getDashboard();
    }
}
