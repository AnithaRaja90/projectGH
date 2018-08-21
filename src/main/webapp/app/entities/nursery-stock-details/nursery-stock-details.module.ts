import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectGhSharedModule } from 'app/shared';
import {
    NurseryStockDetailsComponent,
    NurseryStockDetailsDetailComponent,
    NurseryStockDetailsUpdateComponent,
    NurseryStockDetailsDeletePopupComponent,
    NurseryStockDetailsDeleteDialogComponent,
    nurseryStockDetailsRoute,
    nurseryStockDetailsPopupRoute
} from './';

const ENTITY_STATES = [...nurseryStockDetailsRoute, ...nurseryStockDetailsPopupRoute];

@NgModule({
    imports: [ProjectGhSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryStockDetailsComponent,
        NurseryStockDetailsDetailComponent,
        NurseryStockDetailsUpdateComponent,
        NurseryStockDetailsDeleteDialogComponent,
        NurseryStockDetailsDeletePopupComponent
    ],
    entryComponents: [
        NurseryStockDetailsComponent,
        NurseryStockDetailsUpdateComponent,
        NurseryStockDetailsDeleteDialogComponent,
        NurseryStockDetailsDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhNurseryStockDetailsModule {}
