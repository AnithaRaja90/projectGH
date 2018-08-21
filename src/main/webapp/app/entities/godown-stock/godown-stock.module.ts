import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectGhSharedModule } from 'app/shared';
import {
    GodownStockComponent,
    GodownStockDetailComponent,
    GodownStockUpdateComponent,
    GodownStockDeletePopupComponent,
    GodownStockDeleteDialogComponent,
    godownStockRoute,
    godownStockPopupRoute
} from './';

const ENTITY_STATES = [...godownStockRoute, ...godownStockPopupRoute];

@NgModule({
    imports: [ProjectGhSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GodownStockComponent,
        GodownStockDetailComponent,
        GodownStockUpdateComponent,
        GodownStockDeleteDialogComponent,
        GodownStockDeletePopupComponent
    ],
    entryComponents: [GodownStockComponent, GodownStockUpdateComponent, GodownStockDeleteDialogComponent, GodownStockDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhGodownStockModule {}
