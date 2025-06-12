import { Component, OnInit }     from '@angular/core';
import { CommonModule }          from '@angular/common';
import { VehiculoService, Vehiculo } from '../services/vehiculo.service';
import { RouterModule }          from '@angular/router';

@Component({
  selector: 'app-admin-vehiculos',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './admin-vehiculos.component.html',
  styleUrls: ['./admin-vehiculos.component.css']
})
export class AdminVehiculosComponent implements OnInit {
  vehiculos: Vehiculo[] = [];
  loading = true;
  error: string | null = null;

  constructor(private vehService: VehiculoService) {}

  ngOnInit(): void {
    this.loadVehiculos();
  }

  loadVehiculos(): void {
    this.loading = true;
    this.vehService.getAll().subscribe({
      next: v => { this.vehiculos = v; this.loading = false; },
      error: err => { console.error(err); this.error = 'Error cargando vehículos'; this.loading = false; }
    });
  }

  onDelete(id: number): void {
    if (!confirm('¿Seguro que quieres eliminar este vehículo?')) return;
    this.vehService.delete(id).subscribe({
      next: () => this.vehiculos = this.vehiculos.filter(v => v.idvehiculo !== id),
      error: err => {
        console.error(err);
        alert('Error al eliminar vehículo');
      }
    });
  }
}
