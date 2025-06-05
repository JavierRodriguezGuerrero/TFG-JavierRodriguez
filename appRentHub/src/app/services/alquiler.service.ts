import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlquilerService {
  private readonly API = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  /** Cancela la renovación automática para un alquiler dado */
  cancelRenewal(idAlquiler: number): Observable<void> {
    // Ajusta la URL según tu backend. En este ejemplo:
    return this.http.put<void>(`${this.API}/alquiler/${idAlquiler}/cancelRenewal`, {});
  }
}