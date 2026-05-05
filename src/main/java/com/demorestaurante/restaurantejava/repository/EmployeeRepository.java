package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Employee;
import com.demorestaurante.restaurantejava.model.enums.FootType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByRestaurant_Id(Long id);
List<Employee> findByRestaurantName(String dominosPizza);
List<Employee> findByRestaurant_FoodType(FootType foodType);
List<Employee> findByAgeGreaterThanEqual(Integer age);

//Con este metodo estamos solamente ordenando
@Query("SELECT e FROM Employee e ORDER BY e.firstName")
List<Employee> findByOrderFisrtNameAsc();
}