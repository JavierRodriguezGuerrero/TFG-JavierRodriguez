import { Component, OnInit }             from '@angular/core';
import { CommonModule }                  from '@angular/common';
import {
  ReactiveFormsModule,
  FormBuilder,
  FormGroup,
  Validators
} from '@angular/forms';
import { RouterModule, Router }          from '@angular/router';
import { AuthService }                   from '../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', [Validators.required, Validators.minLength(4)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      name:     ['', Validators.required],
      lastName: ['', Validators.required]
    });
  }

  onRegister(): void {
    if (this.form.invalid) return;
    this.auth.register(this.form.value).subscribe({
      next: () => {
        alert('Registro exitoso, ya puedes iniciar sesión.');
        this.router.navigate(['/login']);
      },
      error: err => {
        console.error(err);
        alert('Error al registrar usuario.');
      }
    });
  }
}
