package com.example.demo.repository;

import com.example.demo.view.IndicatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndicatorRepositoryImpl implements IndicatorRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public IndicatorRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<IndicatorView> findAllWithAvgValue() {
        return jdbcTemplate.query("""
    SELECT indicator.name, COUNT(indicator.employeeId) as employeeCount, ROUND(AVG(indicator.`value`), 2) as averageKpi FROM indicator
    GROUP BY indicator.name""", INDICATOR_VIEW_ROW_MAPPER);
    }
}
