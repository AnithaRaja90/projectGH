import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ProjectGhZonalModule } from './zonal/zonal.module';
import { ProjectGhSectorModule } from './sector/sector.module';
import { ProjectGhNurseryModule } from './nursery/nursery.module';
import { ProjectGhPickListModule } from './pick-list/pick-list.module';
import { ProjectGhPickListValueModule } from './pick-list-value/pick-list-value.module';
import { ProjectGhBatchModule } from './batch/batch.module';
import { ProjectGhDamageModule } from './damage/damage.module';
import { ProjectGhShadeAreaModule } from './shade-area/shade-area.module';
import { ProjectGhNurseryStockModule } from './nursery-stock/nursery-stock.module';
import { ProjectGhNurseryStockDetailsModule } from './nursery-stock-details/nursery-stock-details.module';
import { ProjectGhGodownModule } from './godown/godown.module';
import { ProjectGhGodownPurchaseDetailsModule } from './godown-purchase-details/godown-purchase-details.module';
import { ProjectGhGodownStockModule } from './godown-stock/godown-stock.module';
import { ProjectGhGodownStockDetailsModule } from './godown-stock-details/godown-stock-details.module';
import { ProjectGhMotherBedModule } from './mother-bed/mother-bed.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ProjectGhZonalModule,
        ProjectGhSectorModule,
        ProjectGhNurseryModule,
        ProjectGhPickListModule,
        ProjectGhPickListValueModule,
        ProjectGhBatchModule,
        ProjectGhDamageModule,
        ProjectGhShadeAreaModule,
        ProjectGhNurseryStockModule,
        ProjectGhNurseryStockDetailsModule,
        ProjectGhGodownModule,
        ProjectGhGodownPurchaseDetailsModule,
        ProjectGhGodownStockModule,
        ProjectGhGodownStockDetailsModule,
        ProjectGhMotherBedModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ProjectGhEntityModule {}
