/******************************************************************************
*  Property of Nichehands
*  Nichehands Confidential Proprietary
*  Nichehands Copyright (C) 2018 All rights reserved
*  ----------------------------------------------------------------------------
*  Date: 2018/08/02 11:27:58
*  Target: yarn
*******************************************************************************/
// Angular
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

// Components Routing
import { ConfigurationRoutingModule } from 'app/entities/configuration/configuration.routing.module';
import { ZonalComponent } from 'app/entities/configuration/zonal.component';
import { SectorComponent } from 'app/entities/configuration/sector.component';
import { NurseryComponent } from 'app/entities/configuration/nursery.component';
import { PickListComponent } from 'app/entities/configuration/pick-list.component';

import { ModalModule } from 'ngx-bootstrap/modal';
import { AlertModule } from 'ngx-bootstrap';

@NgModule({
    imports: [CommonModule, FormsModule, ModalModule.forRoot(), AlertModule.forRoot(), ConfigurationRoutingModule, FontAwesomeModule],
    declarations: [ZonalComponent, SectorComponent, NurseryComponent, PickListComponent]
})
export class ConfigurationModule {}
