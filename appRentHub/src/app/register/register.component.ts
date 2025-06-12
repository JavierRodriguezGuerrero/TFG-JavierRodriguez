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
  showSuccess = false;
  showError   = false;

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: [
        '', 
        [
          Validators.required,
          Validators.minLength(4),
          Validators.email        // ← validación de formato email
        ]
      ],
      password: ['', [Validators.required, Validators.minLength(6)]],
      name:     ['', Validators.required],
      lastName: ['', Validators.required]
    });
  }

  onRegister(): void {
    if (this.form.invalid) return;
    this.auth.register(this.form.value).subscribe({
      next: () => this.showSuccess = true,
      error: () => this.showError   = true
    });
  }

  closeSuccess(): void {
    this.showSuccess = false;
  }

  goToLogin(): void {
    this.showSuccess = false;
    this.router.navigate(['/login']);
  }

  closeError(): void {
    this.showError = false;
  }
}

