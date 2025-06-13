import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { RouterModule, Router } from '@angular/router';
import { DireccionService } from '../services/direccion.service';

@Component({
  selector: 'app-new-direccion',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './new-direccion.component.html',
})
export class NewDireccionComponent {
  form: FormGroup;   

  constructor(
    private fb: FormBuilder,
    private dirService: DireccionService,
    private router: Router
  ) {
    
    this.form = this.fb.group({
      calle:        ['', Validators.required],
      ciudad:       ['', Validators.required],
      codigoPostal: ['', Validators.required],
      pais:         ['', Validators.required],
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.dirService.createDireccion(this.form.value).subscribe({
      next: () => this.router.navigate(['/perfil']),
      error: err => console.error('Error creando dirección', err)
    });
  }
}

