package com.example.demo.repository;


import com.example.demo.view.EmployeeView;
import org.springframework.jdbc.core.ResultSetExtractor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public interface EmployeeRepository {

     ResultSetExtractor<List<EmployeeView>> EMPLOYEE_ID_NAME_RS_EXTRACTOR = (ResultSet rs) -> {
         List<EmployeeView> employeesView = new ArrayList<>();

         while(rs.next()) {
             EmployeeView employeeView = new EmployeeView();
             employeeView.setId(rs.getLong("id"));
             employeeView.setName(rs.getString("name"));
             employeesView.add(employeeView);
         }

         return employeesView;
    };

    ResultSetExtractor<List<EmployeeView>> EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR = (ResultSet rs) -> {
        List<EmployeeView> employeesView = new ArrayList<>();

        while(rs.next()) {
                EmployeeView employeeView = new EmployeeView();
                employeeView.setId(rs.getLong("id"));
                employeeView.setName(rs.getString("name"));
                employeeView.setAverageKpi(rs.getDouble("averageKpi"));
                employeesView.add(employeeView);
            }

            return employeesView;
        };

    ResultSetExtractor<List<EmployeeView>> EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR = (ResultSet rs) -> {

        List<EmployeeView> employeeWithSubordinatesView = new ArrayList<>();

        if (rs.next()) {

            EmployeeView employeeView = new EmployeeView();
            employeeView.setName(rs.getString("name"));
            employeeWithSubordinatesView.add(employeeView);

            while (rs.next()) {
                employeeView = new EmployeeView();
                employeeView.setId(rs.getLong("id"));
                employeeView.setName(rs.getString("name"));
                employeeWithSubordinatesView.add(employeeView);
            }
        }

        return employeeWithSubordinatesView;
    };

    List<EmployeeView> findAll();
    List<EmployeeView> findByIdWithSubordinates(Long id);
    List<EmployeeView> findAllWithAverageKpi();
}
