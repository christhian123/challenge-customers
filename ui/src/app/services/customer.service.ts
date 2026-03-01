import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CustomerData } from '../models/customer.model';
import { ApiResponse } from '../models/api-response.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private http = inject(HttpClient);
  private baseUrl = environment.baseUrl;

  getCustomersList(): Observable<ApiResponse<CustomerData[]>> {
    return this.http.get<ApiResponse<CustomerData[]>>(`${this.baseUrl}`);
  }

  createCustomer(customer: CustomerData): Observable<ApiResponse<CustomerData>> {
    return this.http.post<ApiResponse<CustomerData>>(`${this.baseUrl}`, customer);
  }

  updateCustomer(customer: CustomerData): Observable<ApiResponse<CustomerData>> {
    return this.http.put<ApiResponse<CustomerData>>(`${this.baseUrl}`, customer);
  }

  updateCustomerStatus(documentNumber: number): Observable<ApiResponse<CustomerData>> {
    return this.http.put<ApiResponse<CustomerData>>(`${this.baseUrl}/updateStatus/${documentNumber}`, {});
  }

  getCustomer(documentNumber: number): Observable<ApiResponse<CustomerData>> {
    return this.http.get<ApiResponse<CustomerData>>(`${this.baseUrl}/find`, { params: { documentNumber } });
  }
}
