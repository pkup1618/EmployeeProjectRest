package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.example.demo.model.Employee;
import com.example.demo.view.EmployeeView;


public interface EmployeeRepository extends Repository<Employee, Long> {

    @Query(resultSetExtractorClass = EmployeeIdNameRSExtr.class, value = "SELECT * FROM employee")
    List<EmployeeView> findAll();

    @Query(resultSetExtractorClass = EmployeeWithSubordinatesRSExtractor.class, value = """
    SELECT * FROM employee WHERE id = :id 
    UNION ALL 
    SELECT * FROM employee WHERE bossId = :id""")
    List<EmployeeView> findByIdWithSubordinates(Long id);

    @Query(resultSetExtractorClass = EmployeeIdNameAvgKpiRSExtr.class, value = """
    SELECT employeesIndicators .id, employeesIndicators.name, ROUND(AVG(employeesIndicators.`value`),2) as averageKpi FROM
    (SELECT employee.id, employee.name, indicator.`value` FROM employee, indicator
    WHERE indicator.employeeId = employee.id) employeesIndicators 
    GROUP BY employeesIndicators.name""")
    List<EmployeeView> findAllWithAverageKpi();
}

class EmployeeIdNameRSExtr implements ResultSetExtractor<List<EmployeeView>> {

    @Override
    public List<EmployeeView> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<EmployeeView> employeesView = new ArrayList<EmployeeView>();

        while(rs.next()) {
            EmployeeView employeeView = new EmployeeView();
            employeeView.setId(rs.getLong("id"));
            employeeView.setName(rs.getString("name"));
            employeesView.add(employeeView);
        }
        return employeesView;
    }
}

class EmployeeIdNameAvgKpiRSExtr implements ResultSetExtractor<List<EmployeeView>> {

    @Override
    public List<EmployeeView> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<EmployeeView> employeesView = new ArrayList<EmployeeView>();

        while(rs.next()) {
            EmployeeView employeeView = new EmployeeView();
            employeeView.setId(rs.getLong("id"));
            employeeView.setName(rs.getString("name"));
            employeeView.setAverageKpi(rs.getDouble("averageKpi"));
            employeesView.add(employeeView);
        }
        return employeesView;
    }
}

class EmployeeWithSubordinatesRSExtractor implements ResultSetExtractor<List<EmployeeView>> {

    @Override
    public List<EmployeeView> extractData(ResultSet rs) throws SQLException, DataAccessException {
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
    }
}