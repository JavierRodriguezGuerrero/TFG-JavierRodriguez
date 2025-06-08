import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface PurchaseRequest {
  vehiculoId: number;
  cardNumber: string;
  expiry:    string;
  cvv:       string;
}

@Injectable({
  providedIn: 'root'
})
export class RentalService {
  private readonly API = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  rent(request: PurchaseRequest): Observable<void> {
    return this.http.post<void>(`${this.API}/alquiler`, request);
  }
}
