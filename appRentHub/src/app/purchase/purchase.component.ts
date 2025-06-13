import { Component, OnInit }               from '@angular/core';
import { CommonModule }                    from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';

import { RentalService }                   from '../services/rental.service';
import { UserService, UserProfile }        from '../services/user.service';

@Component({
  selector: 'app-purchase',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './purchase.component.html',
  styleUrls: ['./purchase.component.css']
})
export class PurchaseComponent implements OnInit {
  form: FormGroup;
  vehiculoId!: number;
  loading = false;
  error: string | null = null;

  
  showSuccess    = false;
  showError      = false;
  showNoAddress  = false;

  hasAddress = false;

  constructor(
    private fb: FormBuilder,
    private rentalService: RentalService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.form = this.fb.group({
      cardNumber: ['', [Validators.required, Validators.pattern(/^\d{16}$/)]],
      expiry:     ['', [Validators.required, Validators.pattern(/^(0[1-9]|1[0-2])\/\d{2}$/)]],
      cvv:        ['', [Validators.required, Validators.pattern(/^\d{3}$/)]],
    });
  }

  ngOnInit(): void {
    this.vehiculoId = Number(this.route.snapshot.paramMap.get('idvehiculo'));

    
    this.userService.getProfile().subscribe({
      next: (u: UserProfile) => this.hasAddress = !!u.direccion,
      error: () => this.hasAddress = false
    });
  }

  
  get cardIconUrl(): string {
    const num = this.form.get('cardNumber')?.value as string;
    if (/^4/.test(num)) {
      return 'cc-visa-brands-solid.svg';
    }
    if (/^5/.test(num)) {
      return 'cc-mastercard-brands-solid.svg';
    }
    return 'credit-card-solid.svg';  
  }

  onSubmit(): void {
    if (!this.hasAddress) {
      this.showNoAddress = true;
      return;
    }
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const { cardNumber, expiry, cvv } = this.form.value;
    this.loading = true;
    this.error   = null;

    this.rentalService.rent({
      vehiculoId: this.vehiculoId,
      cardNumber,
      expiry,
      cvv
    }).subscribe({
      next: () => {
        this.loading = false;
        this.showSuccess = true;
      },
      error: err => {
        console.error(err);
        this.loading = false;
        this.showError = true;
      }
    });
  }

  closeSuccess(): void {
    this.showSuccess = false;
    this.router.navigate(['/perfil']);
  }

  closeError(): void {
    this.showError = false;
  }

  closeNoAddress(): void {
    this.showNoAddress = false;
    this.router.navigate(['/direccion/nueva']);
  }
}
