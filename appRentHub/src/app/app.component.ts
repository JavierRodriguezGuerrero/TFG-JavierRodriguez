import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CabeceraComponent } from './cabecera/cabecera.component';
import { PiepaginaComponent } from './piepagina/piepagina.component'; 
@Component({
  selector: 'app-root',
  imports: [CabeceraComponent,PiepaginaComponent,RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'appRentHub';
}
