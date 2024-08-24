import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import {
  HTTP_INTERCEPTORS,
  provideHttpClient,
  withFetch,
  withInterceptors,
  withInterceptorsFromDi
} from "@angular/common/http";
import { IConfig } from 'ngx-mask'
import { provideEnvironmentNgxMask } from 'ngx-mask';
import {LoadingInterceptor} from "./services/loading.interceptor";
const maskConfigFunction: () => Partial<IConfig> = () => {
  return {
    validation: false,
  };
};

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideAnimationsAsync(),
    provideHttpClient(withFetch(),withInterceptorsFromDi()),
    provideEnvironmentNgxMask(maskConfigFunction),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: LoadingInterceptor,
      multi: true
    }
  ]
};
