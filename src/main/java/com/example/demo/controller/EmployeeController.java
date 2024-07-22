package com.example.demo.controller;


import java.util.List;

import com.example.demo.view.EmployeeIdNameView;
import com.example.demo.view.EmployeeWithSubordinatesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EmployeeService;
import com.example.demo.view.ErrorView;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeIdNameView> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeWithSubordinatesView getEmployees(@PathVariable Long id) throws ResourceNotFoundException {
        return employeeService.getEmployeeWithSubordinates(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorView handleException(ResourceNotFoundException e) {
        return new ErrorView(e.getMessage());
    }
}