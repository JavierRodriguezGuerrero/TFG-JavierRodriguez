import { Component, OnInit }        from '@angular/core';
import { CommonModule }             from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { HttpClient }               from '@angular/common/http'; 

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.css'
})
export class AdminComponent {
  form!: FormGroup;
  selectedFiles: File[] = [];
  private readonly API_URL = 'http://localhost:8080/vehiculos';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient       // HttpClient ya está disponible
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      marca:       ['', Validators.required],
      modelo:      ['', Validators.required],
      matricula:   ['', Validators.required],
      tipo:        [''],
      precioMes:   [null, [Validators.required, Validators.min(0)]],
      km:          [''],
      fuel:        [''],
      category:    [''],
      transmission:[''],
      descripcion: ['']
    });
  }

  onFilesSelected(event: Event) {
    const input = event.target as HTMLInputElement;
    this.selectedFiles = input.files ? Array.from(input.files) : [];
  }

  onSubmit() {
    if (this.form.invalid) return;

    const formData = new FormData();
    Object.entries(this.form.value).forEach(([k,v]) => {
      if (v != null) formData.append(k, String(v));
    });
    for (const file of this.selectedFiles) {
      formData.append('imagenes', file, file.name);
    }

    this.http.post(this.API_URL, formData).subscribe({
      next: () => {
        alert('Vehículo añadido correctamente');
        this.form.reset();
        this.selectedFiles = [];
      },
      error: err => {
        console.error(err);
        alert('Error al añadir el vehículo');
      }
    });
  }
}