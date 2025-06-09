import { Component, OnInit } from '@angular/core';
import { CommonModule }      from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { DireccionService, Direccion }            from '../services/direccion.service';

@Component({
  selector: 'app-edit-direccion',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './edit-direccion.component.html',
})
export class EditDireccionComponent implements OnInit {
  form!: FormGroup;   // sin inicializar aquí
  id!: number;

  constructor(
    private fb: FormBuilder,
    private dirService: DireccionService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    // inicializamos el formulario _después_ de inyectar fb
    this.form = this.fb.group({
      calle:        ['', Validators.required],
      ciudad:       ['', Validators.required],
      codigoPostal: ['', Validators.required],
      pais:         ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('iddireccion'));
    this.dirService.getDireccion().subscribe({
      next: d => this.form.patchValue(d),
      error: () => console.error('No se pudo cargar la dirección')
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }
    this.dirService.updateDireccion(this.id, this.form.value).subscribe({
      next: () => this.router.navigate(['/perfil']),
      error: err => console.error('Error actualizando dirección', err)
    });
  }
}

