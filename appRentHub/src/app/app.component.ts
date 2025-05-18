import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CabeceraComponent } from './cabecera/cabecera.component';
import { PiepaginaComponent } from './piepagina/piepagina.component'; 
import { CatalogoComponent } from './catalogo/catalogo.component';
@Component({
  selector: 'app-root',
  imports: [CabeceraComponent,PiepaginaComponent,RouterOutlet,CatalogoComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'appRentHub';
}
