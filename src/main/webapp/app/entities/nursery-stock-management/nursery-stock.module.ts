// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// Module Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NurseryStockMgntRoutingModule } from 'app/entities/nursery-stock-management/nursery-stock.routing.module';
import { NurseryStockMgntComponent } from 'app/entities/nursery-stock-management/nursery-stock.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        TabsModule.forRoot(),
        FontAwesomeModule,
        NgbModule.forRoot(),
        NurseryStockMgntRoutingModule
    ],
    declarations: [NurseryStockMgntComponent],
    providers: [],
    exports: [NgbModule]
})

export class NurseryStockMgntModule {}
