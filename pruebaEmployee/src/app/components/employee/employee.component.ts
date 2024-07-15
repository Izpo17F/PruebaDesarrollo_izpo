import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { EmployeeService } from '../../service/employee.service';
import { EmployeeRequest } from '../../model/employeeRequest.interface';
import { EmployeeResponse } from '../../model/employeeResponse.interface';

@Component({
  selector: 'app-employee',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  employeeForm: FormGroup;
  employees: EmployeeResponse[] = [];
  editEmployeeForm: FormGroup;
  isEditModalOpen = false;
  selectedEmployeeId: number | null = null;


  constructor(private fb: FormBuilder, private employeeService: EmployeeService) {
    this.employeeForm = this.fb.group({
      name: ['', Validators.required],
      surname: ['', Validators.required],
      salary: [0, [Validators.required, Validators.min(1)]],
    });
    this.editEmployeeForm = this.fb.group({
      id: [null],
      name: ['', Validators.required],
      surname: ['', Validators.required],
      salary: [0, [Validators.required, Validators.min(1)]],
    });
  }

  ngOnInit(): void {
    this.loadEmployees();
  }

  loadEmployees(): void {
    this.employeeService.getAllEmployees().subscribe((employees) => {
      this.employees = employees;
    });
  }

  registerEmployee(): void {
    if (this.employeeForm.valid) {
      const employeeRequest: EmployeeRequest = this.employeeForm.value;
      this.employeeService.registerEmployee(employeeRequest).subscribe(() => {
        this.employeeForm.reset();
        this.loadEmployees();
      });
    }
  }

  deleteEmployee(id: number): void {
    this.employeeService.deleteEmployee(id).subscribe(() => {
      this.loadEmployees();
    });
  }

  editEmployee(employee: EmployeeResponse): void {
    this.selectedEmployeeId = employee.id;
    this.editEmployeeForm.setValue({
      id: employee.id,
      name: employee.name,
      surname: employee.surname,
      salary: employee.salary,
    });
    this.isEditModalOpen = true;
  }

  updateEmployee(): void {
    if (this.editEmployeeForm.valid) {
      const employeeRequest: EmployeeRequest = {
        name: this.editEmployeeForm.value.name,
        surname: this.editEmployeeForm.value.surname,
        salary: this.editEmployeeForm.value.salary,
      };
      const id = this.editEmployeeForm.value.id;
      this.employeeService.updateEmployee(employeeRequest, id).subscribe(() => {
        this.isEditModalOpen = false;
        this.loadEmployees();
        alert("Empleado actualizado");
      });
    } else {
      alert("El formulario no esta completo");
    }
  }

  closeEditModal(): void {
    this.isEditModalOpen = false;
    this.selectedEmployeeId = null;
  }

  controlHasError(control: string, error: string) {
    const formControl = this.employeeForm.get(control);
    return formControl?.touched && formControl.hasError(error);
  }
}
