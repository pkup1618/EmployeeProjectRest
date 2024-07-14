package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFountException;
import com.example.demo.service.EmployeeService;
import com.example.demo.view.EmployeeView;
import com.example.demo.view.ErrorView;


@RestController
@RequestMapping("/employees")
public class EmployeeController {

    EmployeeService employeeService;
    @Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeView> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeView getEmployees(@PathVariable Long id) throws ResourceNotFountException {
        return employeeService.getEmployeeWithSubordinates(id);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFountException.class)
    public ErrorView handleException(ResourceNotFountException e) {
        return new ErrorView(e.getMessage());
    }
}