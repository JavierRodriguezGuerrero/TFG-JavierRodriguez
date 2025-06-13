import { bootstrapApplication } from '@angular/platform-browser';
import { registerLocaleData }  from '@angular/common';
import localeEs                from '@angular/common/locales/es';

import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { LOCALE_ID }            from '@angular/core';

import { AppComponent } from './app/app.component';
import { appConfig }    from './app/app.config';


registerLocaleData(localeEs, 'es');

bootstrapApplication(AppComponent, {
  
  ...appConfig,
  providers: [
    
    ...(appConfig.providers ?? []),

    
    provideHttpClient(withInterceptorsFromDi()),

    
    { provide: LOCALE_ID, useValue: 'es' }
  ]
})
.catch(err => console.error(err));
