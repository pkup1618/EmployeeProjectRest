package com.example.demo.repository

import com.example.demo.view.IndicatorView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component


@Component
class IndicatorRepositoryImpl @Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) :
    IndicatorRepository {
    override fun findAllWithAvgValue(): List<IndicatorView> {
        val sql = """
                SELECT indicator.name, COUNT(indicator.employeeId) as employeeCount, ROUND(AVG(indicator.`value`), 2) as averageKpi FROM indicator
                GROUP BY indicator.name    
                """.trimIndent()

        val indicators = namedParameterJdbcTemplate.query<IndicatorView>(sql,
            IndicatorRepository.INDICATOR_VIEW_ROW_MAPPER)

        return indicators ?: listOf()
    }
}
