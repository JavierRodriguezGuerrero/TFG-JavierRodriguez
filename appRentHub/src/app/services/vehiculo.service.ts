import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Vehiculo {
  idvehiculo:   number;
  marca:        string;
  modelo:       string;
  precioMes:    number;
  fuel:         string;
  category:     string;
  transmission: string;
  imagenes:     string[];
  disponible:   boolean;
}

@Injectable({ providedIn: 'root' })
export class VehiculoService {
  private readonly API = 'http://localhost:8080/vehiculos';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(this.API);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
