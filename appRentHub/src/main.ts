import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

bootstrapApplication(AppComponent, {
  // mantenemos todo lo que ya estuviera en appConfig…
  ...appConfig,
  providers: [
    // …y añadimos el cliente HTTP con interceptores
    ...(appConfig.providers ?? []),
    provideHttpClient(withInterceptorsFromDi())
  ]
})
.catch(err => console.error(err));
