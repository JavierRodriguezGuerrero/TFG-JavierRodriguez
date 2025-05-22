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
  selector: 'app-login',
  standalone: true,                       // <- imprescindible para usar imports aquí
  imports: [
    CommonModule,
    ReactiveFormsModule,
    RouterModule
  ],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  f!: FormGroup;                          // se inicializa en ngOnInit

  constructor(
    private fb: FormBuilder,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.f = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLogin(): void {
    if (this.f.invalid) return;
    const { username, password } = this.f.value;
    this.auth.login(username, password);   // tu AuthService redirige al éxito
  }
}
