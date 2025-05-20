import { Routes } from '@angular/router';
import { CatalogoComponent } from './catalogo/catalogo.component';
import { InicioComponent } from './inicio/inicio.component';
import { VehiculoDetailComponent } from './vehiculo-detail/vehiculo-detail.component';

export const routes: Routes = [

    {path: '', component:InicioComponent},
    {path: 'catalogo', component:CatalogoComponent},
    { path: 'vehiculos/:id', component: VehiculoDetailComponent },
    { path: '**',         redirectTo: '' }


];
