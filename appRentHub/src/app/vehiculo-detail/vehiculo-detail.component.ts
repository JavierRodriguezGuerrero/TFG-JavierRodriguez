import { Component, OnInit }      from '@angular/core';
import { CommonModule }           from '@angular/common';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HttpClient }             from '@angular/common/http';
import { Observable, switchMap }  from 'rxjs';

interface Vehiculo {
  idvehiculo:   number;
  marca:        string;
  modelo:       string;
  matricula:    string;
  tipo:         string;
  precioMes:    number;
  km:           string;
  fuel:         string;
  category:     string;
  transmission: string;
  descripcion:  string;
  imagenes:     string[];
}


@Component({
  selector: 'app-vehiculo-detail',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './vehiculo-detail.component.html',
  styleUrl: './vehiculo-detail.component.css'
})
export class VehiculoDetailComponent implements OnInit {
  vehiculo$!: Observable<Vehiculo>;
  private readonly API = 'http://localhost:8080/vehiculos';

  constructor(
    private route: ActivatedRoute,
    private http:  HttpClient
  ) {}

  ngOnInit(): void {
    this.vehiculo$ = this.route.paramMap.pipe(
      switchMap(params => {
        const id = params.get('id');
        return this.http.get<Vehiculo>(`${this.API}/${id}`);
      })
    );
  }
}
