import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectGhSharedModule } from 'app/shared';
import {
    NurseryComponent,
    NurseryDetailComponent,
    NurseryUpdateComponent,
    NurseryDeletePopupComponent,
    NurseryDeleteDialogComponent,
    nurseryRoute,
    nurseryPopupRoute
} from './';

const ENTITY_STATES = [...nurseryRoute, ...nurseryPopupRoute];

@NgModule({
    imports: [ProjectGhSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NurseryComponent,
        NurseryDetailComponent,
        NurseryUpdateComponent,
        NurseryDeleteDialogComponent,
        NurseryDeletePopupComponent
    ],
    entryComponents: [NurseryComponent, NurseryUpdateComponent, NurseryDeleteDialogComponent, NurseryDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhNurseryModule {}
