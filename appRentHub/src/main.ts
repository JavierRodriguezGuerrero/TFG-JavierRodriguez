import { bootstrapApplication } from '@angular/platform-browser';
import { registerLocaleData }  from '@angular/common';
import localeEs                from '@angular/common/locales/es';

import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { LOCALE_ID }            from '@angular/core';

import { AppComponent } from './app/app.component';
import { appConfig }    from './app/app.config';

// 1) Registrar los datos de localización para 'es'
registerLocaleData(localeEs, 'es');

bootstrapApplication(AppComponent, {
  // mantenemos todo lo que ya estuviera en appConfig…
  ...appConfig,
  providers: [
    // los providers originales
    ...(appConfig.providers ?? []),

    // 2) Cliente HTTP con interceptores
    provideHttpClient(withInterceptorsFromDi()),

    // 3) Definir el locale por defecto para pipes y formatDate
    { provide: LOCALE_ID, useValue: 'es' }
  ]
})
.catch(err => console.error(err));
