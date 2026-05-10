package com.demorestaurante.restaurantejava.model;
import com.demorestaurante.restaurantejava.model.enums.FoodType;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Restaurantes")
public class Restaurant {
    //Atributos
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;
    private Double averagePrice;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;
    private Integer numberEmployees;
    //@DataTimeFormat(iso = DateTiimeFormat.ISO.DATE )
    private LocalDate startDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    //Asociaciones

    //Constructor
    public Restaurant(){}

    public Restaurant(String name, Double averagePrice, Integer numberEmployees) {
        this.name = name;
        this.averagePrice = averagePrice;
        this.numberEmployees = numberEmployees;
    }

    //Getter y Setter

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Boolean getActive() {
        return active;
    }

    public Integer getNumberEmployees() {
        return numberEmployees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setNumberEmployees(Integer numberEmployee) {
        this.numberEmployees = numberEmployee;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averagePrice=" + averagePrice +
                ", active=" + active +
                ", numberEmployee=" + numberEmployees +
                ", startDate=" + startDate +
                ", footType=" + foodType +
                '}';
    }
}
