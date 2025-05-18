import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule }  from '@angular/forms';

interface Car {
  name: string;
  brand: string;
  price: number;
  fuel: string;
  category: string;
  transmission: string;
  imageUrl: string;
}

@Component({
  selector: 'app-catalogo',
  imports: [CommonModule,             
    FormsModule ],
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.css'
})
export class CatalogoComponent {
  cars: Car[] = [
    { name: 'Aygo X Cross', brand: 'Toyota', price: 288, fuel: 'Gasolina', category: 'Compacto', transmission: 'Manual', imageUrl: 'https://via.placeholder.com/300x180' },
    { name: '208',         brand: 'Peugeot', price: 296, fuel: 'Gasolina', category: 'Compacto', transmission: 'Manual', imageUrl: 'https://via.placeholder.com/300x180' },
    { name: 'Corsa',       brand: 'Opel',    price: 298, fuel: 'Gasolina', category: 'Compacto', transmission: 'Manual', imageUrl: 'https://via.placeholder.com/300x180' },
    { name: 'Yaris',       brand: 'Toyota',  price: 348, fuel: 'Híbrido',  category: 'Compacto', transmission: 'Automático', imageUrl: 'https://via.placeholder.com/300x180' },
    { name: 'Clio',        brand: 'Renault', price: 352, fuel: 'Diésel',   category: 'Compacto', transmission: 'Manual', imageUrl: 'https://via.placeholder.com/300x180' },
    { name: 'Frontera',    brand: 'Opel',    price: 372, fuel: 'Híbrido',  category: 'SUV',      transmission: 'Automático', imageUrl: 'https://via.placeholder.com/300x180' },
  ];

  filterBrand = '';
  filterMaxPrice: number | null = null;
  sortOption: '' | 'priceAsc' | 'priceDesc' | 'name' = '';

  get brands(): string[] {
    return Array.from(new Set(this.cars.map(c => c.brand)));
  }

  get displayedCars(): Car[] {
    const maxPrice = this.filterMaxPrice;      // capturamos en local
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