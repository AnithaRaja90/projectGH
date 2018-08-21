import { enableProdMode } from '@angular/core';
import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { ProdConfig } from './blocks/config/prod.config';
import { ProjectGhAppModule } from './app.module';
import { environment } from '../content/environments/environment';

ProdConfig();

if (module['hot']) {
    module['hot'].accept();
}

if (environment.production) {
    enableProdMode();
}

platformBrowserDynamic()
    .bootstrapModule(ProjectGhAppModule, { preserveWhitespaces: true })
    .then(success => console.log(`Application started`))
    .catch(err => console.error(err));
