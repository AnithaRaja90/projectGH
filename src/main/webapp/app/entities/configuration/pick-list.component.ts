import { Component, OnInit, ViewChild } from '@angular/core';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ActivatedRoute } from '@angular/router';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PickListModel, IPickList } from 'app/shared/model/pick-list.model';
import { PickListValueModel, IPickListValue } from 'app/shared/model/pick-list-value.model';
import { ModalDirective } from 'ngx-bootstrap';
import { IMotherBed, MotherBedModel } from 'app/shared/model/mother-bed.model';
import { MotherBedService } from 'app/entities/service/mother-bed.service';
import { ZonalService } from 'app/entities/service/zonal.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { SectorService } from 'app/entities/service/sector.service';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';

@Component({
    selector: 'jhi-picklist',
    templateUrl: 'pick-list.component.html'
})
export class PickListComponent implements OnInit {
    pickListObject: PickListModel = new PickListModel();
    pickListValueObject: PickListValueModel = new PickListModel();
    motherBedObject: MotherBedModel = new MotherBedModel();
    // create empty array for each service
    pickLists: IPickList[];
    pickListValues: IPickListValue[];
    motherBedList: IMotherBed[];
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    createdAt: string;
    updatedAt: string;
    childCreatedAt: string;
    childUpdatedAt: string;

    @ViewChild('pickListModal') public pickListModal: ModalDirective;
    @ViewChild('pickListValueModal') public pickListValueModal: ModalDirective;
    @ViewChild('subPickListModal') public subPickListModal: ModalDirective;
    @ViewChild('motherBedModal') public motherBedModal: ModalDirective;

    constructor(
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private motherBedService: MotherBedService,
        private zonalService: ZonalService,
        private nurseryService: NurseryService,
        private sectorService: SectorService
    ) {}

    ngOnInit() {
        this.getPickList();
        this.getAllPickListValue();
        this.getMotherBedList();
        this.getZonalList();
    }

    getPickList(): void {
        // Get the list of pickList
        this.pickListService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });
    }

    getAllPickListValue(): void {
        // Get the list of pickListValue
        this.pickListValueService.query().subscribe((res: HttpResponse<IPickListValue[]>) => {
            this.pickListValues = res.body;
        });
    }

    getMotherBedList(): void {
        // Get the list of motherBeds
        this.motherBedService.query().subscribe((res: HttpResponse<IMotherBed[]>) => {
            this.motherBedList = res.body;
        });
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

    getNursery(sectorId): void {
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    savePickList() {
        this.pickListObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.pickListObject.id !== undefined) {
            this.subscribeToSaveResponse(this.pickListService.update(this.pickListObject));
        } else {
            this.pickListObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.pickListService.create(this.pickListObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPickList>>) {
        result.subscribe(
            (res: HttpResponse<IPickList>) => {
                this.pickListModal.hide();
                this.pickListObject = new PickListModel();
                alert('Successfully updated the record.');
                this.getPickList();
            },
            (res: HttpErrorResponse) => {
                // alert('Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    savePickValue() {
        this.pickListValueObject.updatedAt = moment(this.childUpdatedAt, DATE_TIME_FORMAT);
        if (this.pickListValueObject.id !== undefined) {
            this.subscribeToSaveSubPickListResponse(this.pickListValueService.update(this.pickListValueObject));
        } else {
            this.pickListValueObject.createdAt = moment(this.childCreatedAt, DATE_TIME_FORMAT);
            this.subscribeToSaveSubPickListResponse(this.pickListValueService.create(this.pickListValueObject));
        }
    }

    private subscribeToSaveSubPickListResponse(result: Observable<HttpResponse<IPickListValue>>) {
        result.subscribe(
            (res: HttpResponse<IPickListValue>) => {
                this.pickListValueModal.hide();
                this.pickListValueObject = new PickListValueModel();
                alert('Successfully updated the record.');
                this.getAllPickListValue();
            },
            (res: HttpErrorResponse) => {
                // alert('Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create picklist value
    createPickList(): void {
        this.pickListObject = new PickListModel();
        this.pickListModal.show();
    }

    // show model popup to create pickListValue
    createPickListValue(): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueModal.show();
        // console.log(this.pickListValueObject);
    }

    // show model popup to create MotherBed
    showMotherBedCreateForm(): void {
        this.motherBedObject = new MotherBedModel();
        this.motherBedModal.show();
    }

    // show model popup to update pickList value
    getPickListValue(value: PickListModel): void {
        this.pickListModal.show();
        this.pickListObject = value;
    }

    // show model popup to update value
    getPickValue(value: PickListValueModel): void {
        this.pickListValueModal.show();
        this.pickListValueObject = value;
    }

    // delete picklist value
    deletePickList(value: PickListModel): void {
        this.pickListService.delete(value.id).subscribe(data => {
            alert('PickList delete Successfully.');
            this.pickLists = this.pickLists.filter(u => u !== value);
        });
    }

    // delete picklist value
    deletePickListValue(value: PickListValueModel): void {
        this.pickListValueService.delete(value.id).subscribe(data => {
            alert('PickListValue deleted Successfully.');
            this.pickListValues = this.pickListValues.filter(u => u !== value);
        });
    }

    openChildModel(value: PickListValueModel): void {
        this.subPickListModal.show();
        this.pickListValueObject = value;
    }

    // Add sub pick list value
    addChild(val: PickListValueModel): void {
        this.pickListValueObject = new PickListValueModel();
        this.pickListValueObject.pickValueId = val.id;
        this.pickListValueObject.pickListValue = val.subChildValue;
        this.pickListValueObject.createdAt = moment(this.childCreatedAt, DATE_TIME_FORMAT);
        this.pickListValueObject.updatedAt = moment(this.childUpdatedAt, DATE_TIME_FORMAT);
        this.pickListValueService.create(this.pickListValueObject)
            .subscribe(data => {
                this.subPickListModal.hide();
                alert('Sub PickListValue Created Successfully.');
                this.pickListValueObject = new PickListValueModel();
                this.getAllPickListValue();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    saveMotherBed(): void {
        console.log(this.motherBedObject);
        this.motherBedObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.motherBedObject.id !== undefined) {
            this.subscribeToSaveMotherBedResponse(this.motherBedService.update(this.motherBedObject));
        } else {
            this.motherBedObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveMotherBedResponse(this.motherBedService.create(this.motherBedObject));
        }
    }

    private subscribeToSaveMotherBedResponse(result: Observable<HttpResponse<IMotherBed>>) {
        result.subscribe(
            (res: HttpResponse<IMotherBed>) => {
                this.motherBedModal.hide();
                this.motherBedObject = new MotherBedModel();
                alert('MotherBed Created/Updated Successfully.');
                this.getMotherBedList();
            },
            (res: HttpErrorResponse) => {
                // alert('Nursery Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    set pickList(pickListObject: IPickList) {
        this.createdAt = moment(this.pickListObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.pickListObject.updatedAt).format(DATE_TIME_FORMAT);
        this.childCreatedAt = moment(this.pickListValueObject.createdAt).format(DATE_TIME_FORMAT);
        this.childUpdatedAt = moment(this.pickListValueObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
