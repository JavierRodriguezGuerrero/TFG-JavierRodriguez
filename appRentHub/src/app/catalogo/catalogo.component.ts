import { Component, OnInit }        from '@angular/core';
import { CommonModule }             from '@angular/common';
import { FormsModule }              from '@angular/forms';
import { RouterModule }             from '@angular/router';
import { HttpClient }               from '@angular/common/http';
import { map }                      from 'rxjs/operators';
import { Observable }               from 'rxjs';


interface CarRaw {
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

interface Car {
  idvehiculo:  number;
  name:        string;    // raw.marca
  brand:       string;    // raw.modelo
  price:       number;    // raw.precioMes
  fuel:        string;
  category:    string;
  transmission:string;
  imageUrl:    string;
}

@Component({
  selector: 'app-catalogo',
  imports: [CommonModule,             
    FormsModule, RouterModule ],
    standalone: true, 
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent implements OnInit{
  cars: Car[] = [];

  filterBrand = '';
  filterMaxPrice: number | null = null;
  sortOption: '' | 'priceAsc' | 'priceDesc' | 'name' = '';


  private readonly API_URL = 'http://localhost:8080/vehiculos';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.fetchCars().subscribe({
      next: cars => this.cars = cars,
      error: err  => console.error('Error cargando vehículos:', err)
    });
  }

  private fetchCars(): Observable<Car[]> {
    return this.http.get<CarRaw[]>(this.API_URL).pipe(
      map(raws => raws.filter(raw => raw.disponible)),
      map(raws =>
        raws.map(raw => ({
          idvehiculo:   raw.idvehiculo,
          name:         raw.modelo,
          brand:        raw.marca,
          price:        raw.precioMes,
          fuel:         raw.fuel,
          category:     raw.category,
          transmission: raw.transmission,
          imageUrl:     raw.imagenes?.[0] ?? 'https://via.placeholder.com/300x180'
        }))
      )
    );
  }


  get brands(): string[] {
    return Array.from(new Set(this.cars.map(c => c.brand)));
  }

  get displayedCars(): Car[] {
    const maxPrice = this.filterMaxPrice;      
    let arr = [...this.cars];

    if (this.filterBrand) {
      arr = arr.filter(c => c.brand === this.filterBrand);
    }
    if (maxPrice !== null) {
      arr = arr.filter(c => c.price <= maxPrice);
    }

    switch (this.sortOption) {
      case 'priceAsc':
        arr.sort((a,b) => a.price - b.price);
        break;
      case 'priceDesc':
        arr.sort((a,b) => b.price - a.price);
        break;
      case 'name':
        arr.sort((a,b) => a.name.localeCompare(b.name));
        break;
    }
    return arr;
  }

  showFilters = false;

  // alterna la visibilidad
  toggleFilters(): void {
    this.showFilters = !this.showFilters;
  }

  clearFilters(): void {
    this.filterBrand = '';
    this.filterMaxPrice = null;
    this.sortOption = '';
  }


}