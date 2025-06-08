import { Component, OnInit }               from '@angular/core';
import { CommonModule, formatDate }        from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';

import { RentalService }                   from '../services/rental.service';

@Component({
  selector: 'app-purchase',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './purchase.component.html',
  styleUrl: './purchase.component.css'
})
export class PurchaseComponent implements OnInit {
  form!: FormGroup;           // <-- sin inicializar aquí
  vehiculoId!: number;
  loading = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,         // inyectado antes de usarlo
    private rentalService: RentalService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // Inicializamos el form **dentro** del constructor
    this.form = this.fb.group({
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expiry:     ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv:        ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
  }

  ngOnInit(): void {
    this.vehiculoId = Number(this.route.snapshot.paramMap.get('idvehiculo'));
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { cardNumber, expiry, cvv } = this.form.value;
    this.loading = true;

    this.rentalService.rent({
      vehiculoId: this.vehiculoId,
      cardNumber,
      expiry,
      cvv
    }).subscribe({
      next: () => {
        this.loading = false;
        alert('Alquiler y pago registrados correctamente');
        this.router.navigate(['/perfil']);
      },
      error: err => {
        console.error(err);
        this.loading = false;
        this.error = 'Error al procesar el pago. Intenta de nuevo.';
      }
    });
  }
}