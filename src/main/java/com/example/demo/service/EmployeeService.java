package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFountException;
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
        return employeeRepository.findAllWithAverageKpi(order.toUpperCase());
    }

    public EmployeeView getEmployeeWithSubordinates(Long id) throws ResourceNotFountException {
        List<EmployeeView> employeeWithSubordinates = employeeRepository.findByIdWithSubordinates(id);

        if (employeeWithSubordinates.size() == 0) {
            throw new ResourceNotFountException("Resource Employee/" + id + " not found");
        } 

        EmployeeView employeeView = employeeWithSubordinates.remove(0);
        employeeView.setSubordinates(employeeWithSubordinates);

        return employeeView;
    }

    public List<EmployeeView> getAllEmployees() {
        return employeeRepository.findAll();
    }

}