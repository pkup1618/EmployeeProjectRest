package com.example.demo.repository;


import com.example.demo.view.IndicatorView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class IndicatorRepositoryImpl implements IndicatorRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public IndicatorRepositoryImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<IndicatorView> findAllWithAvgValue() {
        String sql = """
                SELECT indicator.name, COUNT(indicator.employeeId) as employeeCount, ROUND(AVG(indicator.`value`), 2) as averageKpi FROM indicator
                GROUP BY indicator.name
                """;

        return namedParameterJdbcTemplate.query(sql, INDICATOR_VIEW_ROW_MAPPER);
    }
}
