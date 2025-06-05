import { Component, OnInit }               from '@angular/core';
import { CommonModule }                    from '@angular/common';
import { FormsModule }                     from '@angular/forms';
import { HttpClient }                      from '@angular/common/http';
import { map }                             from 'rxjs/operators';
import { Observable }                      from 'rxjs';
import { RouterModule }                    from '@angular/router';

interface CarRaw {
  idvehiculo:   number;
  marca:        string;
  modelo:       string;
  precioMes:    number;
  fuel:         string;
  category:     string;
  transmission: string;
  imagenes:     string[];
}

interface Car {
  idvehiculo:   number;
  name:         string;    // modelo
  brand:        string;    // marca
  price:        number;
  fuel:         string;
  category:     string;
  transmission: string;
  images:       string[];  // ← ahora guardamos todas
}


@Component({
  selector: 'app-comparador',
  imports: [
    CommonModule,
    FormsModule,
    RouterModule
  ],
  templateUrl: './comparador.component.html',
  styleUrl: './comparador.component.css'
})
export class ComparadorComponent implements OnInit {
  cars: Car[] = [];
  leftCar: Car | null = null;
  rightCar: Car | null = null;

  private readonly API_URL = 'http://localhost:8080/vehiculos';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchCars().subscribe({
      next: cars => this.cars = cars,
      error: err => console.error('Error cargando vehículos:', err)
    });
  }

  private fetchCars(): Observable<Car[]> {
    return this.http.get<CarRaw[]>(this.API_URL).pipe(
      map(raws =>
        raws.map(raw => ({
          idvehiculo:   raw.idvehiculo,
          name:         raw.modelo,
          brand:        raw.marca,
          price:        raw.precioMes,
          fuel:         raw.fuel,
          category:     raw.category,
          transmission: raw.transmission,
          images:       raw.imagenes && raw.imagenes.length > 0
                          ? raw.imagenes
                          : ['https://via.placeholder.com/300x180']
        }))
      )
    );
  }

  selectVehicle(car: Car, side: 'left' | 'right'): void {
    if (side === 'left') {
      this.leftCar = car;
    } else {
      this.rightCar = car;
    }
  }

  clearSelection(side: 'left' | 'right'): void {
    if (side === 'left') {
      this.leftCar = null;
    } else {
      this.rightCar = null;
    }
  }
}