import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Direccion {
  iddireccion: number;
  calle?: string;
  ciudad?: string;
  codigoPostal?: string;
  provincia?: string;
  pais?: string;
}

export interface Vehiculo {
  idvehiculo:   number;
  marca:        string;
  modelo:       string;
  precioMes:    number;
  fuel:         string;
  category:     string;
  transmission: string;
  imagenes:     string[];
}

export interface Alquiler {
  idalquiler:          number;
  fechaInicio:         string; // puedes ajustar el tipo si lo parseas a Date
  fechaFin:            string;
  renovacionAutomatica: boolean;
  vehiculo:            Vehiculo;
}

export interface UserProfile {
  iduser:    number;
  username:  string;
  name:      string;
  lastName:  string;
  direccion: Direccion | null;
  alquileres: Alquiler[];
}

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private readonly API = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  /** Obtiene el perfil (usuario + dirección + alquileres) */
  getProfile(): Observable<UserProfile> {
    return this.http.get<UserProfile>(`${this.API}/users/me`);
  }
}