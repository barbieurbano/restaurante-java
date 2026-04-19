package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}