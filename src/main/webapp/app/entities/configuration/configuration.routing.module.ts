import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ZonalComponent } from 'app/entities/configuration/zonal.component';
import { SectorComponent } from 'app/entities/configuration/sector.component';
import { NurseryComponent } from 'app/entities/configuration/nursery.component';
import { PickListComponent } from 'app/entities/configuration/pick-list.component';

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Configuration'
        },
        children: [
            {
                path: 'zonal',
                component: ZonalComponent,
                data: {
                    title: 'Zonal'
                }
            },
            {
                path: 'sector',
                component: SectorComponent,
                data: {
                    title: 'Sector'
                }
            },
            {
                path: 'nursery',
                component: NurseryComponent,
                data: {
                    title: 'Nursery'
                }
            },
            {
                path: 'picklist',
                component: PickListComponent,
                data: {
                    title: 'Pick List'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ConfigurationRoutingModule {}
