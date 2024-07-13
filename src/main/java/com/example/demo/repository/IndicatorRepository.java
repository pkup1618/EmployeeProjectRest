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

    @Query(rowMapperClass = MetricViewRowMapper.class, value = """
    SELECT metric.name, COUNT(metric.employeeId) as employeeCount, AVG(metric.`value`) as averageKpi FROM metric
    GROUP BY metric.name""")
    List<IndicatorView> findAllWithAvgValue(String order);
}

class MetricViewRowMapper implements RowMapper<IndicatorView> {

    @Override
    public IndicatorView mapRow(ResultSet rs, int rowNum) throws SQLException {
        IndicatorView indicatorView = new IndicatorView();
        indicatorView.setName(rs.getString("name"));
        indicatorView.setEmployeeCount(rs.getLong("employeeCount"));
        indicatorView.setAverageKpi(rs.getLong("averageKpi"));

        return indicatorView;
    }
}