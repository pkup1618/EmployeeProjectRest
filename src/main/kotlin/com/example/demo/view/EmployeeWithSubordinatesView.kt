package com.example.demo.view


data class EmployeeWithSubordinatesView(val name: String, val subordinates: MutableList<EmployeeIdNameView>)
