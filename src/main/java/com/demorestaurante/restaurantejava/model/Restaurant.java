package com.demorestaurante.restaurantejava.model;
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
    private Boolean active;
    private Integer numberEmployee;
    private LocalDate startDate = LocalDate.now();

    //Asociaciones
    //Constructor
    public Restaurant(){}

    public Restaurant(Long id, String name, Double averagePrice, Boolean active, Integer numberEmployee, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.averagePrice = averagePrice;
        this.active = active;
        this.numberEmployee = numberEmployee;
        this.startDate = startDate;
    }
    //Getter y Setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public void setNumberEmployee(Integer numberEmployee) {
        this.numberEmployee = numberEmployee;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averagePrice=" + averagePrice +
                ", active=" + active +
                ", numberEmployee=" + numberEmployee +
                ", startDate=" + startDate +
                '}';
    }
}
