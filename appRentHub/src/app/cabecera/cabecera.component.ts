import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { CommonModule }   from '@angular/common';

@Component({
  selector: 'app-cabecera',
  imports: [RouterLink,CommonModule],
  templateUrl: './cabecera.component.html',
  styleUrl: './cabecera.component.css'
})
export class CabeceraComponent {
  constructor(public auth: AuthService) {}
}
