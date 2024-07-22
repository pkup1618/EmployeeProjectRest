package com.example.demo.view;


import java.util.List;


public record EmployeeWithSubordinatesView(String name, List<EmployeeIdNameView> subordinates) {
}
