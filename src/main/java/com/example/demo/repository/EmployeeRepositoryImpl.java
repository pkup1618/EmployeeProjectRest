package com.example.demo.repository;


import java.util.List;

import com.example.demo.view.EmployeeAvgKpiView;
import com.example.demo.view.EmployeeIdNameView;
import com.example.demo.view.EmployeeWithSubordinatesView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;


@Component
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final NamedParameterJdbcTemplate namedParameterjdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(NamedParameterJdbcTemplate namedParameterjdbcTemplate) {
        this.namedParameterjdbcTemplate = namedParameterjdbcTemplate;
    }

    @Override
    public List<EmployeeIdNameView> findAll() {
        String sql = "SELECT * FROM employee";
        return namedParameterjdbcTemplate.query(sql, EMPLOYEE_ID_NAME_RS_EXTRACTOR);
    }

    @Override
    public List<EmployeeWithSubordinatesView> findByIdWithSubordinates(Long id) {
        String sql = """
                SELECT * FROM employee WHERE id = :id
                UNION ALL
                SELECT * FROM employee WHERE bossId = :id
                """;

        SqlParameterSource namedParameters = new MapSqlParameterSource("id", id);

        return namedParameterjdbcTemplate.query(sql, namedParameters, EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR);
    }

    @Override
    public List<EmployeeAvgKpiView> findAllWithAverageKpi() {
        String sql = """
                SELECT employeesIndicators .id, employeesIndicators.name, ROUND(AVG(employeesIndicators.`value`),2) as averageKpi FROM
                (SELECT employee.id, employee.name, indicator.`value` FROM employee, indicator
                WHERE indicator.employeeId = employee.id) employeesIndicators
                GROUP BY employeesIndicators.name
                """;

        return namedParameterjdbcTemplate.query(sql, EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR);
    }
}