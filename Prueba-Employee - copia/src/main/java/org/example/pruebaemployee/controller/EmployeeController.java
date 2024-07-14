package org.example.pruebaemployee.controller;

import org.example.pruebaemployee.model.dto.EmployeeRequestDTO;
import org.example.pruebaemployee.model.dto.EmployeeResponseDTO;
import org.example.pruebaemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/save")
    public ResponseEntity<EmployeeResponseDTO> registerEmployee(@Validated @RequestBody EmployeeRequestDTO employeeRequestDTO) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.saveEmployee(employeeRequestDTO);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<EmployeeResponseDTO>> getListEmployees() {
        List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EmployeeResponseDTO> deleteEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponseDTO employeeResponseDTO = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(employeeResponseDTO, HttpStatus.OK);
    }

}
