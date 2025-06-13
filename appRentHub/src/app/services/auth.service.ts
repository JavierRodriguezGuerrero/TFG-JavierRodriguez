import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router }     from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private api = 'http://localhost:8080/auth';
  constructor(private http: HttpClient, private router: Router) {}

  register(data: { username:string; password:string; name:string; lastName:string; }) {
    return this.http.post(`${this.api}/register`, data);
  }

  login(username: string, password: string) {
    const creds = btoa(`${username}:${password}`);
    
    return this.http.post(`${this.api}/login`,
      { username, password },
      { headers: { Authorization: `Basic ${creds}` } }
    ).subscribe({
      next: (res: any) => {
        localStorage.setItem('credentials', creds);
        localStorage.setItem('roles', JSON.stringify(res.roles || []));
        this.router.navigate(['/']);
      },
      error: () => alert('Credenciales incorrectas')
    });
  }

  logout() {
    localStorage.removeItem('credentials');
    localStorage.removeItem('roles');
    this.router.navigate(['/login']);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  getRoles(): string[] {
    return JSON.parse(localStorage.getItem('roles') || '[]');
  }

  isLogged(): boolean {
    return !!this.getCredentials();
  }

  hasRole(role: string): boolean {
    return this.getRoles().includes(role);
  }
}
