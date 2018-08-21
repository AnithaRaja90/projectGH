// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { ZonalService } from 'app/entities/service/zonal.service';
import { SectorService } from 'app/entities/service/sector.service';
import { ActivatedRoute } from '@angular/router';
import { ISector, SectorModel } from 'app/shared/model/sector.model';
import { IZonal } from 'app/shared/model/zonal.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-sector',
    templateUrl: 'sector.component.html'
})
/**
 * Class SectorComponent used to create/update a sector, List all sectors.
 * Declared an sector object to create and update.
 * Declared an Array variable to set list of sectors.
 * Using a modal popup directive create and update form is displayed.
 */
export class SectorComponent implements OnInit {
    // Create object for model
    sectorObject: SectorModel = new SectorModel();
    // create empty array for each service
    zonals: IZonal[];
    sectors: ISector[];
    createdAt: string;
    updatedAt: string;

    @ViewChild('sectorModal') public sectorModal: ModalDirective;

    constructor(private zonalService: ZonalService, private sectorService: SectorService) {}

    ngOnInit() {
        // Call a function to get list of zonals and sectors
        this.getZoneList();
        this.getSectorList();
    }

    // Call a service function to get list of zonals
    getZoneList(): void {
        // Get the list of zone
        this.zonalService.query().subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });
    }

    // Call a service function to get list of sectors
    getSectorList(): void {
        // Get the list of zone
        this.sectorService.query().subscribe((res: HttpResponse<ISector[]>) => {
            this.sectors = res.body;
        });
    }

    // Send a sector object to a service (create or update)
    save() {
        // console.log(this.sectorObject);
        this.sectorObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.sectorObject.id !== undefined) {
            this.subscribeToSaveResponse(this.sectorService.update(this.sectorObject));
        } else {
            this.sectorObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.sectorService.create(this.sectorObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISector>>) {
        result.subscribe(
            (res: HttpResponse<ISector>) => {
                this.sectorModal.hide();
                this.sectorObject = new SectorModel();
                alert('Sector Created/Updated Successfully.');
                this.getSectorList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create sector value
    createSector(): void {
        this.sectorObject = new SectorModel();
        this.sectorModal.show();
    }

    // show model popup to update sector value
    getSectorValue(value: SectorModel): void {
        this.sectorModal.show();
        this.sectorObject = value;
    }

    // delete sector value
    deleteSector(sector: SectorModel): void {
        this.sectorService.delete(sector.id).subscribe(data => {
            alert('Sector deleted Successfully.');
            this.sectors = this.sectors.filter(u => u !== sector);
        });
    }

    // Set a current time to a variable
    set sector(sectorObject: SectorModel) {
        this.createdAt = moment(this.sectorObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.sectorObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
