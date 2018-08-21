// Import needed component and dependency
import { Component, OnInit } from '@angular/core';
import { GodownService } from 'app/entities/service/godown.service';
import { IGodown, GodownModel } from 'app/shared/model/godown.model';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { ViewChild } from '@angular/core';

import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

// Mension the html, css/sass files
@Component({
    selector: 'jhi-godown',
    templateUrl: 'godown.component.html'
})

/**
 * Class GodownComponent used to create/update a godown, List all godowns.
 * Declared an Godown object to create and update.
 * Declared an Array variable to set list of godowns.
 * Using a modal popup directive create and update form is displayed.
 */
export class GodownComponent implements OnInit {
    // Create object for model
    godownObject: GodownModel = new GodownModel();
    // create empty array for each service
    godowns: IGodown[];
    createdAt: string;
    updatedAt: string;
    // Declare a modal popup
    @ViewChild('godownModal') public godownModal: ModalDirective;

    constructor(private godownService: GodownService) {}

    ngOnInit() {
        // Call a function to get list of godowns
        this.getGodownList();
    }

    // Call a service function to get list of godowns
    getGodownList(): void {
        // Get the list of godown
        this.godownService.query().subscribe((res: HttpResponse<IGodown[]>) => {
            this.godowns = res.body;
        });
    }

    // Send a godown object to a service (create or update)
    save() {
        this.godownObject.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        if (this.godownObject.id !== undefined) {
            this.subscribeToSaveResponse(this.godownService.update(this.godownObject));
        } else {
            this.godownObject.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
            this.subscribeToSaveResponse(this.godownService.create(this.godownObject));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGodown>>) {
        result.subscribe(
            (res: HttpResponse<IGodown>) => {
                this.godownModal.hide();
                this.godownObject = new GodownModel();
                alert('Godown Created/Updated Successfully.');
                this.getGodownList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // show model popup to create godown value
    createGodown(): void {
        this.godownObject = new GodownModel();
        this.godownModal.show();
    }

    // show model popup to update godown value
    getGodownValue(value: GodownModel): void {
        this.godownModal.show();
        this.godownObject = value;
    }

    // delete godown value
    deleteGodown(godown: GodownModel): void {
        this.godownService.delete(godown.id).subscribe(data => {
            alert('Godown deleted Successfully.');
            this.godowns = this.godowns.filter(u => u !== godown);
        });
    }

    // Set a current time to a variable
    set godown(godownObject: GodownModel) {
        this.createdAt = moment(this.godownObject.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.godownObject.updatedAt).format(DATE_TIME_FORMAT);
    }
}
