package com.example.demo.service;

import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.exception.ResourceNotFoundException;
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
        List<EmployeeView> employees = employeeRepository.findAllWithAverageKpi();

        if (order == null) {
            order = "";
        }
        
        switch(order) {
            case "asc": {
                employees.sort(Comparator.comparingDouble(EmployeeView::getAverageKpi));
                break;
            }
            case "desc": {
                employees.sort((o1, o2) -> -Double.compare(o1.getAverageKpi(), o2.getAverageKpi()));
                break;
            }
            default: {
                employees.sort(Comparator.comparing(EmployeeView::getName));
                break;
            }
        }
            
        return employees;
    }

    public EmployeeView getEmployeeWithSubordinates(Long id) throws ResourceNotFoundException {
        List<EmployeeView> employeeWithSubordinates = employeeRepository.findByIdWithSubordinates(id);

        if (employeeWithSubordinates.isEmpty()) {
            throw new ResourceNotFoundException("Resource Employee/" + id + " not found");
        } 

        EmployeeView employeeView = employeeWithSubordinates.remove(0);
        employeeView.setSubordinates(employeeWithSubordinates);

        return employeeView;
    }

    public List<EmployeeView> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
