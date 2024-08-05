package com.example.demo.repository

import com.example.demo.view.IndicatorView
import org.springframework.jdbc.core.RowMapper
import java.sql.ResultSet


interface IndicatorRepository {
    fun findAllWithAvgValue(): List<IndicatorView>

    companion object {
        val INDICATOR_VIEW_ROW_MAPPER: RowMapper<IndicatorView> = RowMapper { rs: ResultSet, _: Int ->
            val indicatorView = IndicatorView(
                rs.getString("name"),
                rs.getLong("employeeCount"),
                rs.getDouble("averageKpi")
            )
            indicatorView
        }
    }
}

