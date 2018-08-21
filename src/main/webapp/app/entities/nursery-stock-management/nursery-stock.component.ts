// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import { INurseryStock } from 'app/shared/model/nursery-stock.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import { HttpResponse } from '@angular/common/http';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-godown',
    templateUrl: 'nursery-stock.component.html'
})

/**
 * Class NurseryStockMgntComponent used to create/update a godown, List all nurseryStocks.
 * Declared an Array variable to set list of nursery stocks.
 */
export class NurseryStockMgntComponent implements OnInit {
    // create empty array for each service
    nurseryStocks: INurseryStock[];

    constructor(private nurseryStockService: NurseryStockService) {}

    ngOnInit() {
        // Call a function to get list of nursery Stocks
        this.getNurseryStockList();
    }

    // Call a service function to get list of stocks
    getNurseryStockList(): void {
        // Get the list of godown
        this.nurseryStockService.query().subscribe((res: HttpResponse<INurseryStock[]>) => {
            this.nurseryStocks = res.body;
        });
    }
}
