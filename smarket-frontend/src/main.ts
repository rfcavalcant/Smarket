import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config'; // ou './main' dependendo de onde moveu
import { AppComponent } from './app/app.component';

bootstrapApplication(AppComponent, appConfig)
  .catch(err => console.error(err));
