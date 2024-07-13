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
        List<EmployeeView> employees = employeeRepository.findAllWithAverageKpi();

        if (order == null) {
            employees.sort(new Comparator<EmployeeView>() {

                @Override
                public int compare(EmployeeView o1, EmployeeView o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            return employees;
        }
        
        switch(order) {
            case "asc": {
                employees.sort(new Comparator<EmployeeView>() {

                    @Override
                    public int compare(EmployeeView o1, EmployeeView o2) {
                        return Double.compare(o1.getAverageKpi(), o2.getAverageKpi());
                    }
                });
                break;
            }
            case "desc": {
                employees.sort(new Comparator<EmployeeView>() {
                    @Override
                    public int compare(EmployeeView o1, EmployeeView o2) {
                        return 0 - Double.compare(o1.getAverageKpi(), o2.getAverageKpi());
                    }
                });
                break;
            }
            default: {
                break;
            }
        }
            
        return employees;
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