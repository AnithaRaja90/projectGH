// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { GodownPurchaseDetailsService } from 'app/entities/service/godown-purchase-details.service';
import { IGodownPurchaseDetails, GodownPurchaseDetailsModel } from 'app/shared/model/godown-purchase-details.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-godown-purchase',
    templateUrl: 'godown-purchase.component.html'
})

/**
 * Class GodownPurchaseComponent used to create/update a godownPurchase, List all godownPurchases.
 * Declared an GodownPurchase object to create and update.
 * Declared an Array variable to set list of godownPurchases.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownPurchaseComponent implements OnInit {
    // Create object for model
    godownPurchaseObject: GodownPurchaseDetailsModel = new GodownPurchaseDetailsModel();
    // create empty array for each service
    godownPurchases: IGodownPurchaseDetails[];
    createdAt: string;
    updatedAt: string;
    pickLists: IPickList[];
    varietys: IPickListValue[];

    // Declare a modal popup
    @ViewChild('godownPurchaseModal') public godownPurchaseModal: ModalDirective;

    constructor(private godownPurchaseService: GodownPurchaseDetailsService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService) {}

    ngOnInit() {
        // Call a function to get list of godownPurchases
        this.getGodownPurchaseList();
        // to get the pick list
        this.getPickList();
        // to get the quantity variety
        // this.getVariety(id);
    }

    // Call a service function to get list of godowns
    getGodownPurchaseList(): void {
        // Get the list of godownPurchase
        this.godownPurchaseService.query().subscribe((res: HttpResponse<IGodownPurchaseDetails[]>) => {
            this.godownPurchases = res.body;
        });
    }

    // Send a godownPurchase object to a service (create or update)
    save() {
        this.godownPurchaseObject.date = moment(this.godownPurchaseObject.date, DATE_TIME_FORMAT);
        this.godownPurchaseObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.godownPurchaseObject.id !== undefined) {
            this.subscribeToSaveResponse(this.godownPurchaseService.update(this.godownPurchaseObject));
        } else {
            this.godownPurchaseObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.godownPurchaseService.create(this.godownPurchaseObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodownPurchaseDetails>>) {
        result.subscribe(
            (res: HttpResponse<IGodownPurchaseDetails>) => {
                this.godownPurchaseModal.hide();
                this.godownPurchaseObject = new GodownPurchaseDetailsModel();
                alert('Purchase Details Created/Updated Successfully.');
                this.getGodownPurchaseList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create godownPurchase value
    createGodownPurchase(): void {
        this.godownPurchaseModal.show();
    }

    // show model popup to update godownPurchase value
    getGodownPurchaseValue(value: GodownPurchaseDetailsModel): void {
        this.godownPurchaseModal.show();
        this.godownPurchaseObject = value;
    }

    getPickList(): void {
        // Get the list of picklist
        this.pickListService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
            // console.log("pick", this.pickLists);
        });
    }

    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varietys = res.body;
        });
    }

    // delete godownPurchase value
    deleteGodownPurchase(godownPurchase: GodownPurchaseDetailsModel): void {
        this.godownPurchaseService.delete(godownPurchase.id).subscribe(data => {
            alert('Purchase Details deleted Successfully.');
            this.godownPurchases = this.godownPurchases.filter(u => u !== godownPurchase);
        });
    }

    // Set a current time to a variable
    set godownPurchase(godownPurchaseObject: GodownPurchaseDetailsModel) {
        this.createdAt = moment(this.godownPurchaseObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.godownPurchaseObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
