package com.example.demo.repository;


import java.sql.ResultSet;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import com.example.demo.view.IndicatorView;


public interface IndicatorRepository {

    RowMapper<IndicatorView> INDICATOR_VIEW_ROW_MAPPER = (ResultSet rs, int rowNum) -> {
        IndicatorView indicatorView = new IndicatorView(
                rs.getString("name"),
                rs.getLong("employeeCount"),
                rs.getDouble("averageKpi"));

        return indicatorView;
    };

    List<IndicatorView> findAllWithAvgValue();
}

