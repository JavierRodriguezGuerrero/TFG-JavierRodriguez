import { Component, OnInit }       from '@angular/core';
import { CommonModule }            from '@angular/common';
import { UserService, UserProfile, Direccion, Alquiler } from '../services/user.service';
import { AlquilerService }         from '../services/alquiler.service';
import { RouterModule }            from '@angular/router';
import { formatDate }              from '@angular/common';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule,
    RouterModule],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent implements OnInit {
  user!: UserProfile;
  loading = true;
  errorMessage: string | null = null;

  constructor(
    private userService: UserService,
    private alquilerService: AlquilerService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.userService.getProfile().subscribe({
      next: u => {
        this.user = u;
        this.loading = false;
      },
      error: err => {
        console.error(err);
        this.errorMessage = 'No se pudo cargar la información del perfil.';
        this.loading = false;
      }
    });
  }

  /**  
   * Devuelve un string con la fecha de renovación formateada.  
   * Si no hay renovación, devuelve un texto alternativo.  
   */
  getRenewalDate(al: Alquiler): string {
    if (al.renovacionAutomatica) {
      // Por ejemplo, formatea fechaFin como 'dd-MM-yyyy'
      return formatDate(al.fechaFin, 'dd-MM-yyyy', 'es-ES');
    }
    return 'Renovación desactivada';
  }

  /** Invoca el servicio para cancelar la renovación y refresca la lista localmente */
  onCancelRenewal(al: Alquiler): void {
    this.alquilerService.cancelRenewal(al.idalquiler).subscribe({
      next: () => {
        // Marca localmente que esta renovación ya no está activa 
        al.renovacionAutomatica = false;
      },
      error: err => {
        console.error(err);
        alert('Error al cancelar la renovación.');
      }
    });
  }

  onLogout(): void {
    this.authService.logout();
  }

  public hasRole(role: string): boolean {
    return this.authService.hasRole(role);
  }

}
