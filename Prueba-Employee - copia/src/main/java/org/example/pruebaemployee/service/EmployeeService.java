package org.example.pruebaemployee.service;

import lombok.AllArgsConstructor;
import org.example.pruebaemployee.mapper.EmployeeMapper;
import org.example.pruebaemployee.model.dto.EmployeeRequestDTO;
import org.example.pruebaemployee.model.dto.EmployeeResponseDTO;
import org.example.pruebaemployee.model.entity.Employee;
import org.example.pruebaemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeResponseDTO saveEmployee(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee();
        employee.setName(employeeRequestDTO.getName());
        employee.setSurname(employeeRequestDTO.getSurname());
        employee.setSalary(employeeRequestDTO.getSalary());
        return employeeMapper.convertToDto(employeeRepository.save(employee));
    }
    public List<EmployeeResponseDTO> getAllEmployees(){
        return employeeMapper.convertToListDto(employeeRepository.findAll());
    }
    public EmployeeResponseDTO updateEmployee(EmployeeRequestDTO employeeRequestDTO, Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("El usuario no fue encontrado"));
        employee.setName(employeeRequestDTO.getName());
        employee.setSurname(employeeRequestDTO.getSurname());
        employee.setSalary(employeeRequestDTO.getSalary());
        return employeeMapper.convertToDto(employeeRepository.save(employee));
    }
    public EmployeeResponseDTO deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado no fue encontrado"));
        employeeRepository.delete(employee);
        return employeeMapper.convertToDto(employee);
    }
    public EmployeeResponseDTO getEmployeeById(Long id){
        return employeeMapper.convertToDto(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("El empleado no fue encontrado")));
    }
}
