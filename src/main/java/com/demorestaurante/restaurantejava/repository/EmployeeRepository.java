package com.demorestaurante.restaurantejava.repository;

import com.demorestaurante.restaurantejava.model.Employee;
import com.demorestaurante.restaurantejava.model.enums.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
List<Employee> findByRestaurant_Id(Long id);
List<Employee> findByRestaurantName(String dominosPizza);
List<Employee> findByRestaurant_FoodType(FoodType foodType);
List<Employee> findByAgeGreaterThanEqual(Integer age);

//Con este metodo estamos solamente ordenando
@Query("select e from Employee e order by e.firstName")
    List<Employee> findByOrderByFirstNameAsc();
    List<Employee> findByDni(String dni);
    List<Employee> findByAge(Integer age);
    List<Employee> findByLastName(String lastName);
    List<Employee> findByFirstName(String name);
    List<Employee> findByRestaurantAveragePrice(Double averagePrice);

    // SPRING DATA JPA --> HIBERNATE (JPA) --> JDBC --> H2 / MYSQL / POSTGRESQ

}