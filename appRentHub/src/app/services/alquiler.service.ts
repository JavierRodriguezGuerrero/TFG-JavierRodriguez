import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlquilerService {
  private readonly API = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  
  cancelRenewal(idAlquiler: number): Observable<void> {
    
    return this.http.put<void>(`${this.API}/alquiler/${idAlquiler}/cancelRenewal`, {});
  }
}