package com.example.demo.repository

import com.example.demo.view.EmployeeAvgKpiView
import com.example.demo.view.EmployeeIdNameView
import com.example.demo.view.EmployeeWithSubordinatesView
import org.springframework.jdbc.core.ResultSetExtractor
import java.sql.ResultSet
import java.util.*


interface EmployeeRepository {
    fun findAll(): List<EmployeeIdNameView>

    fun findByIdWithSubordinates(id: Long): List<EmployeeWithSubordinatesView>

    fun findAllWithAverageKpi(): List<EmployeeAvgKpiView>

    companion object {
        val EMPLOYEE_ID_NAME_RS_EXTRACTOR: ResultSetExtractor<List<EmployeeIdNameView>> =
            ResultSetExtractor { rs: ResultSet ->
                val employees: MutableList<EmployeeIdNameView> = ArrayList()
                while (rs.next()) {
                    val employeeView = EmployeeIdNameView(
                        rs.getLong("id"),
                        rs.getString("name")
                    )
                    employees.add(employeeView)
                }
                employees
            }

        val EMPLOYEE_ID_NAME_AVG_KPI_RS_EXTRACTOR: ResultSetExtractor<List<EmployeeAvgKpiView>> =
            ResultSetExtractor { rs: ResultSet ->
                val employees: MutableList<EmployeeAvgKpiView> = ArrayList()
                while (rs.next()) {
                    val employeeView = EmployeeAvgKpiView(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getDouble("averageKpi")
                    )
                    employees.add(employeeView)
                }
                employees
            }

        val EMPLOYEE_WITH_SUBORDINATES_RS_EXTRACTOR: ResultSetExtractor<List<EmployeeWithSubordinatesView>> =
            ResultSetExtractor { rs: ResultSet ->
                val employeeWithSubordinatesView: MutableList<EmployeeWithSubordinatesView> = ArrayList()
                if (rs.next()) {
                    val employee = EmployeeWithSubordinatesView(rs.getString("name"), LinkedList())

                    employeeWithSubordinatesView.add(employee)

                    while (rs.next()) {
                        val subordinate = EmployeeIdNameView(
                            rs.getLong("id"),
                            rs.getString("name")
                        )
                        employee.subordinates.add(subordinate)
                    }
                }
                employeeWithSubordinatesView
            }
    }
}
