package org.example.pruebaemployee.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequestDTO {
    @NotBlank(message = "El nombre no puede ser nulo")
    private String name;
    @NotBlank(message = "El apellido no puede ser nulo")
    private String surname;
    @NotNull(message = "El salario no puede ser vacio")
    private Double salary;
}
