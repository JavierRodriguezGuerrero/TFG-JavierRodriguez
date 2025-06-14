import { Routes } from '@angular/router';
import { CatalogoComponent } from './catalogo/catalogo.component';
import { InicioComponent } from './inicio/inicio.component';
import { VehiculoDetailComponent } from './vehiculo-detail/vehiculo-detail.component';
import { AdminComponent } from './admin/admin.component';
import { LoginComponent }           from './login/login.component';
import { RegisterComponent }        from './register/register.component';
import { AuthGuard }                from './guards/auth.guard';
import { RoleGuard } from './guards/role.guard';
import { ComparadorComponent } from './comparador/comparador.component';
import { PerfilComponent } from './perfil/perfil.component';
import { PurchaseComponent } from './purchase/purchase.component';
import { NewDireccionComponent } from './new-direccion/new-direccion.component';
import { EditDireccionComponent } from './edit-direccion/edit-direccion.component';
import { AdminVehiculosComponent } from './admin-vehiculos/admin-vehiculos.component';

export const routes: Routes = [

    {path: '', component:InicioComponent},
    {path: 'catalogo', component:CatalogoComponent},
    { path: 'vehiculos/:id', component: VehiculoDetailComponent },
    { 
        path: 'admin', 
        component: AdminComponent,
        canActivate: [RoleGuard],
        data: { roles: ['ROLE_ADMIN'] }
    },
    { 
        path: 'admin/vehiculos', 
        component: AdminVehiculosComponent,
        canActivate: [RoleGuard],
        data: { roles: ['ROLE_ADMIN'] }
    },
    {
        path: 'perfil',
        component: PerfilComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'compra/:idvehiculo',
        component: PurchaseComponent,
        canActivate: [AuthGuard]
    },
    { path: 'login',      component: LoginComponent },
    { path: 'register',   component: RegisterComponent },
    { path: 'comparador',   component: ComparadorComponent },
    { path: 'direccion/nueva', component: NewDireccionComponent },
    { path: 'direccion/editar/:iddireccion', component: EditDireccionComponent },
    { path: '**',         redirectTo: '' }


];
