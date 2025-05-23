import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { AuthService } from '../services/auth.service';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
  constructor(private auth: AuthService) {}
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const creds = this.auth.getCredentials();
    if (creds) {
      req = req.clone({
        setHeaders: { Authorization: `Basic ${creds}` }
      });
    }
    return next.handle(req);
  }
}
