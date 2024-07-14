package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.model.Indicator;
import com.example.demo.model.IndicatorId;
import com.example.demo.view.IndicatorView;


public interface IndicatorRepository extends Repository<Indicator, IndicatorId> {

    @Query(rowMapperClass = IndicatorViewRowMapper.class, value = """
    SELECT indicator.name, COUNT(indicator.employeeId) as employeeCount, ROUND(AVG(indicator.`value`), 2) as averageKpi FROM indicator
    GROUP BY indicator.name""")
    List<IndicatorView> findAllWithAvgValue();
}

class IndicatorViewRowMapper implements RowMapper<IndicatorView> {

    @Override
    public IndicatorView mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndicatorView indicatorView = new IndicatorView();
        indicatorView.setName(rs.getString("name"));
        indicatorView.setEmployeeCount(rs.getLong("employeeCount"));
        indicatorView.setAverageKpi(rs.getDouble("averageKpi"));

        return indicatorView;
    }
}