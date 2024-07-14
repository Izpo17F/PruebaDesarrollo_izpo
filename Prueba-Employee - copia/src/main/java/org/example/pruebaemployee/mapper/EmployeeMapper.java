package org.example.pruebaemployee.mapper;

import lombok.AllArgsConstructor;
import org.example.pruebaemployee.model.dto.EmployeeResponseDTO;
import org.example.pruebaemployee.model.entity.Employee;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class EmployeeMapper {
    private final ModelMapper modelMapper;

    public Employee convertToEntity(EmployeeResponseDTO employee){
        return modelMapper.map(employee, Employee.class);
    }
    public EmployeeResponseDTO convertToDto(Employee employee){
        return modelMapper.map(employee, EmployeeResponseDTO.class);
    }
    public List<EmployeeResponseDTO> convertToListDto(List<Employee> employees){
        return employees.stream()
                .map(this::convertToDto)
                .toList();
    }
}
