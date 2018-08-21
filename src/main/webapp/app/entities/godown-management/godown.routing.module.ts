import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GodownComponent } from 'app/entities/godown-management/godown.component';
import { GodownPurchaseComponent } from 'app/entities/godown-management/godown-purchase.component';

const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Godown Management'
        },
        children: [
            {
                path: 'godownlist',
                component: GodownComponent,
                data: {
                    title: 'Godown Details'
                }
            },
            {
                path: 'purchase',
                component: GodownPurchaseComponent,
                data: {
                    title: 'Purchase Details'
                }
            },
            {
                path: 'stock',
                data: {
                    title: 'Stock List'
                }
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class GodownRoutingModule {}
