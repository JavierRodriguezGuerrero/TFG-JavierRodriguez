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

    
    provideHttpClient(
      withInterceptorsFromDi()      
    ),

    
    {
      provide: HTTP_INTERCEPTORS,
      useClass: BasicAuthInterceptor,
      multi: true
    },

    
    importProvidersFrom(FormsModule)
  ]
};
