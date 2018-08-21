import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BatchmgntComponent } from 'app/entities/batch-management/batchmgnt.component';

const routes: Routes = [
    {
        path: '',
        component: BatchmgntComponent,
        data: {
            title: 'Batch Formation'
        }
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BatchmgntRoutingModule {}
