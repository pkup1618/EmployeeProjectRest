package com.example.demo.controller

import com.example.demo.exception.ResourceNotFoundException
import com.example.demo.service.EmployeeService
import com.example.demo.view.EmployeeIdNameView
import com.example.demo.view.EmployeeWithSubordinatesView
import com.example.demo.view.ErrorView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/employees")
class EmployeeController @Autowired constructor(private val employeeService: EmployeeService) {
    @get:GetMapping
    val employees: List<EmployeeIdNameView>
        get() = employeeService.getAllEmployees()

    @GetMapping("/{id}")
    @Throws(ResourceNotFoundException::class)
    fun getEmployees(@PathVariable id: Long): EmployeeWithSubordinatesView {
        return employeeService.getEmployeeWithSubordinates(id)
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException::class)
    fun handleException(e: ResourceNotFoundException): ErrorView {
        return ErrorView(e.message!!)
    }
}