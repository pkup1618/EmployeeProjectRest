package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.IndicatorService;
import com.example.demo.view.EmployeeView;
import com.example.demo.view.IndicatorView;


@RestController
public class IndicatorController {

    EmployeeService employeeService;
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    IndicatorService indicatorService;
    @Autowired
    public void setIndicatorService(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    EmployeeRepository employeeRepository;
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/kpiByEmployee")
    public List<EmployeeView> getKpiByEmployee(@RequestParam(value="order", required = false) String order) {
        return employeeService.getAllEmployeesWithAverageKpi(order);
    }

    @GetMapping("/kpi")
    public List<IndicatorView> getKpi(@RequestParam(value="order", required =  false) String order) {
        return indicatorService.getAllIndicatorsWithAverageValue(order);
    }
}