// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { ZonalService } from 'app/entities/service/zonal.service';
import { ActivatedRoute } from '@angular/router';
import { IZonal, ZonalModel } from 'app/shared/model/zonal.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-zonal',
    templateUrl: 'zonal.component.html'
})

/**
 * Class ZonalComponent used to create/update a zonal, List all zonals.
 * Declared an Zonal object to create and update.
 * Declared an Array variable to set list of zonals.
 * Using a modal popup directive create and update form is displayed.
 */
export class ZonalComponent implements OnInit {
    // Create object for model
    zonalObject: ZonalModel = new ZonalModel();
    // create empty array for each service
    zonals: IZonal[];
    createdAt: string;
    updatedAt: string;
    // Declare a modal popup
    @ViewChild('zoneModal') public zoneModal: ModalDirective;

    constructor(private zonalService: ZonalService) {}

    ngOnInit() {
        // Call a function to get list of zonals
        this.getZoneList();
    }

    // Call a service function to get list of zonals
    getZoneList(): void {
        // Get the list of zone
        this.zonalService.query().subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Send a zonal object to a service (create or update)
    save() {
        this.zonalObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.zonalObject.id !== undefined) {
            this.subscribeToSaveResponse(this.zonalService.update(this.zonalObject));
        } else {
            this.zonalObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.zonalService.create(this.zonalObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IZonal>>) {
        result.subscribe(
            (res: HttpResponse<IZonal>) => {
                this.zoneModal.hide();
                this.zonalObject = new ZonalModel();
                alert('Zone Created/Updated Successfully.');
                this.getZoneList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create zonal value
    createZone(): void {
        this.zonalObject = new ZonalModel();
        this.zoneModal.show();
    }

    // show model popup to update zonal value
    getZoneValue(value: ZonalModel): void {
        this.zoneModal.show();
        this.zonalObject = value;
    }

    // delete zonal value
    deleteZonal(zonal: ZonalModel): void {
        this.zonalService.delete(zonal.id).subscribe(data => {
            alert('Zone deleted Successfully.');
            this.zonals = this.zonals.filter(u => u !== zonal);
        });
    }

    // Set a current time to a variable
    set zonal(zonalObject: ZonalModel) {
        this.createdAt = moment(this.zonalObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.zonalObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
