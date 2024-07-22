package com.example.demo.service;


import java.util.Comparator;
import java.util.List;

import com.example.demo.view.EmployeeAvgKpiView;
import com.example.demo.view.EmployeeIdNameView;
import com.example.demo.view.EmployeeWithSubordinatesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;


@Service
public class EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeAvgKpiView> getAllEmployeesWithAverageKpi(String order) {
        List<EmployeeAvgKpiView> employees = employeeRepository.findAllWithAverageKpi();

        if (order == null) {
            order = "";
        }

        switch (order) {
            case "asc": {
                employees.sort(Comparator.comparingDouble(EmployeeAvgKpiView::averageKpi));
                break;
            }
            case "desc": {
                employees.sort((o1, o2) -> -Double.compare(o1.averageKpi(), o2.averageKpi()));
                break;
            }
            default: {
                employees.sort(Comparator.comparing(EmployeeAvgKpiView::name));
                break;
            }
        }

        return employees;
    }

    public EmployeeWithSubordinatesView getEmployeeWithSubordinates(Long id) throws ResourceNotFoundException {
        List<EmployeeWithSubordinatesView> employeeWithSubordinates = employeeRepository.findByIdWithSubordinates(id);

        if (employeeWithSubordinates.isEmpty()) {
            throw new ResourceNotFoundException("Resource Employee/" + id + " not found");
        }

        EmployeeWithSubordinatesView employee = employeeWithSubordinates.get(0);
        return employee;
    }

    public List<EmployeeIdNameView> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
