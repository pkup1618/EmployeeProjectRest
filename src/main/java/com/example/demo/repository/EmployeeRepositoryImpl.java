package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.example.demo.view.EmployeeView;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<EmployeeView> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", EMPLOYEE_ID_NAME_RS_EXTRACTOR);
    }

    @Override
    public List<EmployeeView> findByIdWithSubordinates(Long id) {
        return jdbcTemplate.query("""
    SELECT * FROM employee WHERE id = :id
    UNION ALL
    SELECT * FROM employee WHERE bossId = :id""", EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR);
    }

    @Override
    public List<EmployeeView> findAllWithAverageKpi() {
        return jdbcTemplate.query("""
    SELECT employeesIndicators .id, employeesIndicators.name, ROUND(AVG(employeesIndicators.`value`),2) as averageKpi FROM
    (SELECT employee.id, employee.name, indicator.`value` FROM employee, indicator
    WHERE indicator.employeeId = employee.id) employeesIndicators
    GROUP BY employeesIndicators.name""", EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR);
    }
}