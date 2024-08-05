package com.example.demo.service

import com.example.demo.exception.ResourceNotFoundException
import com.example.demo.repository.EmployeeRepository
import com.example.demo.view.EmployeeAvgKpiView
import com.example.demo.view.EmployeeIdNameView
import com.example.demo.view.EmployeeWithSubordinatesView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
class EmployeeService @Autowired constructor(
    private val employeeRepository: EmployeeRepository) {

    fun getAllEmployeesWithAverageKpi(order: String?): List<EmployeeAvgKpiView> {
        val employees = employeeRepository.findAllWithAverageKpi()

        when (order) {
            "asc" -> {
                employees.sortedWith(Comparator.comparingDouble(EmployeeAvgKpiView::averageKpi))
            }
            "desc" -> {
                employees.sortedWith { o1: EmployeeAvgKpiView, o2: EmployeeAvgKpiView ->
                    -o1.averageKpi.compareTo(o2.averageKpi)
                }
            }
            else -> {
                employees.sortedWith(Comparator.comparing(EmployeeAvgKpiView::name))
            }
        }

        return employees
    }

    @Throws(ResourceNotFoundException::class)
    fun getEmployeeWithSubordinates(id: Long): EmployeeWithSubordinatesView {
        val employeeWithSubordinates = employeeRepository.findByIdWithSubordinates(id)

        if (employeeWithSubordinates.isEmpty()) {
            throw ResourceNotFoundException("Resource Employee/$id not found")
        }

        val employee = employeeWithSubordinates[0]

        return employee
    }

    fun getAllEmployees(): List<EmployeeIdNameView> {
        return employeeRepository.findAll()
    }
}
