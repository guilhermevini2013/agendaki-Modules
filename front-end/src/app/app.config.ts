import {ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';
import {IConfig, provideEnvironmentNgxMask} from 'ngx-mask'
import {routes} from './app.routes';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {provideHttpClient, withFetch} from "@angular/common/http";

const maskConfigFunction: () => Partial<IConfig> = () => {
  return {
    validation: false,
  };
};
export const appConfig: ApplicationConfig = {
  providers: [provideEnvironmentNgxMask(maskConfigFunction), provideZoneChangeDetection({eventCoalescing: true}), provideRouter(routes), provideAnimationsAsync(), provideHttpClient(withFetch())]
};
