package com.example.demo.controller

import com.example.demo.service.EmployeeService
import com.example.demo.service.IndicatorService
import com.example.demo.view.EmployeeAvgKpiView
import com.example.demo.view.IndicatorView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class IndicatorController @Autowired constructor(
    private val employeeService: EmployeeService,
    private val indicatorService: IndicatorService
) {
    @GetMapping("/kpiByEmployee")
    fun getKpiByEmployee(@RequestParam(value = "order", required = false) order: String?): List<EmployeeAvgKpiView> {
        return employeeService.getAllEmployeesWithAverageKpi(order)
    }

    @GetMapping("/kpi")
    fun getKpi(@RequestParam(value = "order", required = false) order: String?): List<IndicatorView> {
        return indicatorService.getAllIndicatorsWithAverageValue(order)
    }
}