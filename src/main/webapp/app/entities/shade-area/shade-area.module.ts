import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProjectGhSharedModule } from 'app/shared';
import {
    ShadeAreaComponent,
    ShadeAreaDetailComponent,
    ShadeAreaUpdateComponent,
    ShadeAreaDeletePopupComponent,
    ShadeAreaDeleteDialogComponent,
    shadeAreaRoute,
    shadeAreaPopupRoute
} from './';

const ENTITY_STATES = [...shadeAreaRoute, ...shadeAreaPopupRoute];

@NgModule({
    imports: [ProjectGhSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        ShadeAreaComponent,
        ShadeAreaDetailComponent,
        ShadeAreaUpdateComponent,
        ShadeAreaDeleteDialogComponent,
        ShadeAreaDeletePopupComponent
    ],
    entryComponents: [ShadeAreaComponent, ShadeAreaUpdateComponent, ShadeAreaDeleteDialogComponent, ShadeAreaDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhShadeAreaModule {}
