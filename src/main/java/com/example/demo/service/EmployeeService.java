package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.view.EmployeeView;


@Service
public class EmployeeService {
    
    EmployeeRepository employeeRepository;
    
    @Autowired
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeView> getAllEmployeesWithAverageKpi(String order) {
        return employeeRepository.findAllWithAverageKpi(order);
    }

    public EmployeeView getEmployeeWithSubordinates(Long id) {
        List<EmployeeView> employeeWithSubordinates = employeeRepository.findByIdWithSubordinates(id);
        EmployeeView bossView = employeeWithSubordinates.remove(0);
        bossView.setSubordinates(employeeWithSubordinates);

        return bossView;
    }

    public List<EmployeeView> getAllEmployees() {
        return employeeRepository.findAll();
    }

}