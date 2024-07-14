//src/env/env.ts
import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EmployeeRequest } from '../model/employeeRequest.interface';
import { EmployeeResponse } from '../model/employeeResponse.interface';
import { env } from '../../env/env';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private httpClient = inject(HttpClient);
  private baseUrl = env.baseURL;

  constructor() { }

  registerEmployee(employeeRequest: EmployeeRequest): Observable<EmployeeResponse> {
    return this.httpClient.post<EmployeeResponse>(`${this.baseUrl}employee/save`, employeeRequest);
  }

  getAllEmployees(): Observable<EmployeeResponse[]> {
    return this.httpClient.get<EmployeeResponse[]>(`${this.baseUrl}employee/getAll`);
  }

  deleteEmployee(id: number): Observable<void> {
    return this.httpClient.delete<void>(`${this.baseUrl}employee/delete/${id}`);
  }
}
