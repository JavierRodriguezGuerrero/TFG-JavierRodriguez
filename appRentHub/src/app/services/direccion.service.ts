import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Direccion {
  iddireccion:   number;
  calle:         string;
  ciudad:        string;
  provincia:     string;
  codigoPostal:  string;
  pais:          string;
}

@Injectable({ providedIn: 'root' })
export class DireccionService {
  private readonly API = 'http://localhost:8080/users/me/direccion';

  constructor(private http: HttpClient) {}

  getDireccion(): Observable<Direccion> {
    return this.http.get<Direccion>(this.API);
  }
  createDireccion(dir: Omit<Direccion,'iddireccion'>): Observable<Direccion> {
    return this.http.post<Direccion>(this.API, dir);
  }
  updateDireccion(id: number, dir: Omit<Direccion,'iddireccion'>): Observable<Direccion> {
    return this.http.put<Direccion>(`${this.API}/${id}`, dir);
  }
}