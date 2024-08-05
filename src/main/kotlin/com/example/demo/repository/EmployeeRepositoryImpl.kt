package com.example.demo.repository

import com.example.demo.view.EmployeeAvgKpiView
import com.example.demo.view.EmployeeIdNameView
import com.example.demo.view.EmployeeWithSubordinatesView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.SqlParameterSource
import org.springframework.stereotype.Component


@Component
class EmployeeRepositoryImpl @Autowired constructor(private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate) :
    EmployeeRepository {

    override fun findAll(): List<EmployeeIdNameView> {
        val sql = "SELECT * FROM employee"
        val employees = namedParameterJdbcTemplate.query(sql, EmployeeRepository.EMPLOYEE_ID_NAME_RS_EXTRACTOR)

        return employees ?: listOf()
    }

    override fun findByIdWithSubordinates(id: Long): List<EmployeeWithSubordinatesView> {
        val sql = """
                SELECT * FROM employee WHERE id = :id
                UNION ALL
                SELECT * FROM employee WHERE bossId = :id
                """.trimIndent()
        val namedParameters: SqlParameterSource = MapSqlParameterSource("id", id)

        val employees = namedParameterJdbcTemplate.query(sql, namedParameters,
            EmployeeRepository.EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR
        )

        return employees ?: listOf()
    }

    override fun findAllWithAverageKpi(): List<EmployeeAvgKpiView> {
        val sql = """
                SELECT employeesIndicators .id, employeesIndicators.name, ROUND(AVG(employeesIndicators.`value`),2) as averageKpi FROM
                (SELECT employee.id, employee.name, indicator.`value` FROM employee, indicator
                WHERE indicator.employeeId = employee.id) employeesIndicators
                GROUP BY employeesIndicators.name
                """.trimIndent()

        val employees = namedParameterJdbcTemplate.query(sql, EmployeeRepository.EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR)

        return employees ?: listOf()
    }
}