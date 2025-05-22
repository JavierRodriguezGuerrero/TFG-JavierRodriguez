import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  Router
} from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class RoleGuard implements CanActivate {
  constructor(private auth: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const required = route.data['roles'] as string[];
    if (!this.auth.isLogged() || !required.some(r => this.auth.hasRole(r))) {
      alert('Acceso denegado');
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
