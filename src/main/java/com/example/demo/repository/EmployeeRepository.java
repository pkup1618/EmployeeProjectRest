package com.example.demo.repository;


import com.example.demo.view.EmployeeAvgKpiView;
import com.example.demo.view.EmployeeIdNameView;
import com.example.demo.view.EmployeeWithSubordinatesView;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public interface EmployeeRepository {

    ResultSetExtractor<List<EmployeeIdNameView>> EMPLOYEE_ID_NAME_RS_EXTRACTOR = (ResultSet rs) -> {
        List<EmployeeIdNameView> employees = new ArrayList<>();

        while (rs.next()) {
            EmployeeIdNameView employeeView = new EmployeeIdNameView(
                    rs.getLong("id"),
                    rs.getString("name"));
            employees.add(employeeView);
        }

        return employees;
    };

    ResultSetExtractor<List<EmployeeAvgKpiView>> EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR = (ResultSet rs) -> {
        List<EmployeeAvgKpiView> employees = new ArrayList<>();

        while (rs.next()) {
            EmployeeAvgKpiView employeeView = new EmployeeAvgKpiView(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getDouble("averageKpi"));
            employees.add(employeeView);
        }

        return employees;
    };

    ResultSetExtractor<List<EmployeeWithSubordinatesView>> EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR = (ResultSet rs) -> {
        List<EmployeeWithSubordinatesView> employeeWithSubordinatesView = new ArrayList<>();

        if (rs.next()) {
            EmployeeWithSubordinatesView employee = new EmployeeWithSubordinatesView(
                    rs.getString("name"), new LinkedList<>());

            employeeWithSubordinatesView.add(employee);

            while (rs.next()) {
                EmployeeIdNameView subordinate = new EmployeeIdNameView(
                        rs.getLong("id"),
                        rs.getString("name"));

                employee.subordinates().add(subordinate);
            }
        }

        return employeeWithSubordinatesView;
    };

    List<EmployeeIdNameView> findAll();

    List<EmployeeWithSubordinatesView> findByIdWithSubordinates(Long id);

    List<EmployeeAvgKpiView> findAllWithAverageKpi();
}
