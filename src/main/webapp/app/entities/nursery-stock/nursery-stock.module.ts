import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectGhSharedModule } from 'app/shared';
import {
    NurseryStockComponent,
    NurseryStockDetailComponent,
    NurseryStockUpdateComponent,
    NurseryStockDeletePopupComponent,
    NurseryStockDeleteDialogComponent,
    nurseryStockRoute,
    nurseryStockPopupRoute
} from './';

const ENTITY_STATES = [...nurseryStockRoute, ...nurseryStockPopupRoute];

@NgModule({
    imports: [ProjectGhSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryStockComponent,
        NurseryStockDetailComponent,
        NurseryStockUpdateComponent,
        NurseryStockDeleteDialogComponent,
        NurseryStockDeletePopupComponent
    ],
    entryComponents: [
        NurseryStockComponent,
        NurseryStockUpdateComponent,
        NurseryStockDeleteDialogComponent,
        NurseryStockDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhNurseryStockModule {}
