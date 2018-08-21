// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { NurseryService } from 'app/entities/service/nursery.service';
import { SectorService } from 'app/entities/service/sector.service';
import { ActivatedRoute } from '@angular/router';
import { INursery, NurseryModel } from 'app/shared/model/nursery.model';
import { ISector } from 'app/shared/model/sector.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ZonalService } from 'app/entities/service/zonal.service';
import { IZonal } from 'app/shared/model/zonal.model';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-nursery',
    templateUrl: 'nursery.component.html'
})

/**
 * Class NurseryComponent used to create/update a nursery, List all nurserys.
 * Declared an Nursery object to create and update.
 * Declared an Array variable to set list of Nurserys.
 * Using a modal popup directive create and update form is displayed.
 */
export class NurseryComponent implements OnInit {
    nurseryObject: NurseryModel = new NurseryModel();
    // create empty array for each service
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    createdAt: string;
    updatedAt: string;
    // Declare a modal popup
    @ViewChild('nurseryModal') public nurseryModal: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private nurseryService: NurseryService,
        private sectorService: SectorService
    ) {}
    ngOnInit() {
        // Call a function to get list of zonals and Nurserys
        this.getZonalList();
        this.getNurseryList();
    }

    // Call a service function to get list of zonals
    getZonalList(): void {
        // Get the list of zone
        this.zonalService.query().subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Get the sector value based on zonal id
    getSector(zoneId): void {
        // Get the list of sector
        this.sectorService.getSectors(zoneId).subscribe((res: HttpResponse<ISector[]>) => {
            this.sectors = res.body;
        });
    }

    // Call a service function to get list of Nurserys
    getNurseryList(): void {
        this.nurseryService.query().subscribe((res: HttpResponse<INursery[]>) => {
            this.nurserys = res.body;
        });
    }

    save() {
        this.nurseryObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.nurseryObject.id !== undefined) {
            this.subscribeToSaveResponse(this.nurseryService.update(this.nurseryObject));
        } else {
            this.nurseryObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.nurseryService.create(this.nurseryObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INursery>>) {
        result.subscribe(
            (res: HttpResponse<INursery>) => {
                this.nurseryModal.hide();
                this.nurseryObject = new NurseryModel();
                alert('Nursery Created/Updated Successfully.');
                this.getNurseryList();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create nursery value
    createNursery(): void {
        this.nurseryObject = new NurseryModel();
        this.nurseryModal.show();
    }

    // show model popup to update nursery value
    getNurseryValue(value: NurseryModel): void {
        this.nurseryModal.show();
        this.nurseryObject = value;
    }

    // delete nursery value
    deleteNursery(nursery: NurseryModel): void {
        this.nurseryService.delete(nursery.id).subscribe(data => {
            alert('Nursery deleted Successfully.');
            this.nurserys = this.nurserys.filter(u => u !== nursery);
        });
    }

    // Set a current time to a variable
    set nursery(nurseryObject: INursery) {
        this.createdAt = moment(this.nurseryObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.nurseryObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
