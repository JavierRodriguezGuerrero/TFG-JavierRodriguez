import { ApplicationConfig, provideZoneChangeDetection, importProvidersFrom } from '@angular/core';
import { provideRouter }       from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule }         from '@angular/forms';

import { routes }                 from './app.routes';
import { BasicAuthInterceptor }   from './interceptors/basic-auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),

    // en lugar de provideHttpClient(), usamos withInterceptorsFromDi()
    provideHttpClient(
      withInterceptorsFromDi()      // esto recoge todos los HTTP_INTERCEPTORS registrados
    ),

    // registramos nuestro interceptor
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthInterceptor,
      multi: true
    },

    // para ngModel en formularios
    importProvidersFrom(FormsModule)
  ]
};
