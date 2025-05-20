import { Routes } from '@angular/router';
import { CatalogoComponent } from './catalogo/catalogo.component';
import { InicioComponent } from './inicio/inicio.component';

export const routes: Routes = [

    {path: '', component:InicioComponent},
    {path: 'catalogo', component:CatalogoComponent},



];
