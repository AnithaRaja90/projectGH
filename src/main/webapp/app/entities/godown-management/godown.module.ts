// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// Module Component
import { ModalModule } from 'ngx-bootstrap/modal';
import { TabsModule } from 'ngx-bootstrap/tabs';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { GodownRoutingModule } from 'app/entities/godown-management/godown.routing.module';
import { GodownComponent } from 'app/entities/godown-management/godown.component';
import { GodownPurchaseComponent } from 'app/entities/godown-management/godown-purchase.component';

@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        ModalModule.forRoot(),
        TabsModule.forRoot(),
        FontAwesomeModule,
        NgbModule.forRoot(),
        GodownRoutingModule
    ],
    declarations: [GodownComponent, GodownPurchaseComponent],
    providers: [],
    exports: [NgbModule]
})

export class GodownModule {}
