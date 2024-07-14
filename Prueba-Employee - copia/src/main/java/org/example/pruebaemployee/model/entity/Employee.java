package org.example.pruebaemployee.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable=false, length=50)
    private String name;
    @Column(name="surname", nullable=false, length=50)
    private String surname;
    @Column(name="salary", nullable=false)
    private Double salary;
}
