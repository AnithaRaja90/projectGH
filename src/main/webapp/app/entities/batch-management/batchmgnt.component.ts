import { Component, OnInit, ViewChild, Injectable } from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';
import { IZonal } from 'app/shared/model/zonal.model';
import { ISector } from 'app/shared/model/sector.model';
import { INursery } from 'app/shared/model/nursery.model';
import { IPickList } from 'app/shared/model/pick-list.model';
import { IPickListValue } from 'app/shared/model/pick-list-value.model';
import { IBatch, BatchModel } from 'app/shared/model/batch.model';
import { IShadeArea, ShadeAreaModel } from 'app/shared/model/shade-area.model';
import { NurseryStockModel, STATUS_ACTIVE, INurseryStock } from 'app/shared/model/nursery-stock.model';
import { NurseryStockDetailsModel, STATUS_ADD } from 'app/shared/model/nursery-stock-details.model';
import { IDamage, DamageModel, STATUS_SEEDS, STATUS_SEEDLING } from 'app/shared/model/damage.model';
import { ZonalService } from 'app/entities/service/zonal.service';
import { NurseryService } from 'app/entities/service/nursery.service';
import { BatchService } from 'app/entities/service/batch.service';
import { DamageService } from 'app/entities/service/damage.service';
import { PickListService } from 'app/entities/service/pick-list.service';
import { PickListValueService } from 'app/entities/service/pick-list-value.service';
import { ShadeAreaService } from 'app/entities/service/shade-area.service';
import { NurseryStockService } from 'app/entities/service/nursery-stock.service';
import { NurseryStockDetailsService } from 'app/entities/service/nursery-stock-details.service';

import * as moment from 'moment';
import { DATE_TIME_FORMAT, MONTHS } from 'app/shared';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SectorService } from 'app/entities/service/sector.service';
import { map } from 'rxjs/operators';

@Component({
    selector: 'jhi-batchmgnt',
    templateUrl: 'batchmgnt.component.html'
})
export class BatchmgntComponent implements OnInit {
    // Create object of each model for create form
    batch: BatchModel = new BatchModel();
    updateBatchValue: BatchModel = new BatchModel();
    damage: DamageModel = new DamageModel();
    shadeArea: ShadeAreaModel = new ShadeAreaModel();
    nurseryStock: NurseryStockModel = new NurseryStockModel();
    nurseryStockDetails: NurseryStockDetailsModel = new NurseryStockDetailsModel();
    createdAt: string;
    updatedAt: string;
    sowingDateDp: any;
    closedDateDp: any;
    dateDamageDp: any;
    dateShadeAreaDp: any;
    fromDate: any;
    toDate: any;

    // create empty array for each service
    zonals: IZonal[];
    sectors: ISector[];
    nurserys: INursery[];
    pickLists: IPickList[];
    varietys: IPickListValue[];
    categorys: IPickListValue[];
    batchs: IBatch[];
    shadeAreas: IShadeArea[];
    damages: IDamage[];
    months = MONTHS;
    stockStatus = STATUS_ACTIVE;
    addStockStatus = STATUS_ADD;

    @ViewChild('closeBatchModal') public closeBatchModal: ModalDirective;
    @ViewChild('damageModal') public damageModal: ModalDirective;
    @ViewChild('shiftBatchModal') public shiftBatchModal: ModalDirective;
    @ViewChild('shadeAreaRecordModal') public shadeAreaRecordModal: ModalDirective;
    @ViewChild('damageRecordModal') public damageRecordModal: ModalDirective;
    @ViewChild('stockModal') public stockModal: ModalDirective;

    constructor(
        private zonalService: ZonalService,
        private sectorService: SectorService,
        private nurseryService: NurseryService,
        private pickListService: PickListService,
        private pickListValueService: PickListValueService,
        private batchService: BatchService,
        private damageService: DamageService,
        private shadeAreaService: ShadeAreaService,
        private nurseryStockService: NurseryStockService,
        private nurseryStockDetailsService: NurseryStockDetailsService
    ) {}

    ngOnInit() {
        // console.log("Inside Batch Component");
        // Get the list of zone
        this.zonalService.query().subscribe((res: HttpResponse<IZonal[]>) => {
            this.zonals = res.body;
        });

        // Get the list of picklist
        this.pickListService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.pickLists = res.body;
        });

        // Get the list of batch
        this.getBatchList();
    }

    getBatchList(): void {
        // Get the list of batch
        this.batchService.query().subscribe((res: HttpResponse<IBatch[]>) => {
            this.batchs = res.body;
        });
    }

    getShadeAreaList(): void {
        // console.log('Indide getShadeAreaList');
        // Get the list of shade area
        this.shadeAreaService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.shadeAreas = res.body;
        });
    }

    getDamageList(): void {
        // console.log('Indide getDamageList');
        // Get the list of damage
        this.damageService.query().subscribe((res: HttpResponse<IPickList[]>) => {
            this.damages = res.body;
        });
    }

    // Get the sector value based on zonal id
    getSector(zoneId): void {
        // console.log(zoneId);
        // Get the list of sector
        this.sectorService.getSectors(zoneId).subscribe((res: HttpResponse<ISector[]>) => {
            // console.log(res.body);
            this.sectors = res.body;
        });
    }

    getNursery(sectorId): void {
        // this.nurseryService.getSectorNursery(sectorId)
        // .subscribe( data => {
        //   this.sectorNurserys = data;
        // });
        // Get the list of nursery
        this.nurseryService.getNurserys(sectorId).subscribe((res: HttpResponse<INursery[]>) => {
            // console.log(res.body);
            this.nurserys = res.body;
        });
    }

    getVariety(id): void {
        this.pickListValueService.getVariety(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.varietys = res.body;
        });
        // this.pickListValueService.getParentList(id)
        // .subscribe( data => {
        //   this.variety = data;
        // });
    }

    getCategory(id): void {
        this.pickListValueService.getCategory(id).subscribe((res: HttpResponse<IPickListValue[]>) => {
            // console.log(res.body);
            this.categorys = res.body;
        });
        // this.pickListValueService.getChildList(id)
        // .subscribe( data => {
        //   this.categorys = data;
        // });
    }

    // Create new Batch
    save() {
        // console.log(this.batch);
        this.batch.createdAt = moment(this.createdAt, DATE_TIME_FORMAT);
        this.batch.updatedAt = moment(this.updatedAt, DATE_TIME_FORMAT);
        this.batch.sowingDate = moment(this.batch.sowingDate, DATE_TIME_FORMAT);
        // console.log(this.batch);
        this.subscribeToSaveResponse(this.batchService.create(this.batch));
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IBatch>>) {
        result.subscribe(
            (res: HttpResponse<IBatch>) => {
                this.batch = new BatchModel();
                alert('Batch Created Successfully.');
                this.getBatchList();
            },
            (res: HttpErrorResponse) => {
                // alert('Batch Not Saved.');
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Delete the batch
    deleteBatch(batch: BatchModel): void {
        this.batchService.delete(batch.id).subscribe(data => {
            this.batchs = this.batchs.filter(u => u !== batch);
            alert('Successfully Deleted.');
        });
    }

    // show model popup to close the batch
    updateBatch(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        this.closeBatchModal.show();
        this.updateBatchValue = value;
    }

    // show model popup to update the damage
    updateDamage(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.damageModal.show();
        this.updateBatchValue = value;
        this.updateBatchValue.status = STATUS_SEEDS;
    }

    // show model popup to shift the seed to shade area
    updateShift(value: BatchModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.shiftBatchModal.show();
        this.updateBatchValue = value;
    }

    // show model popup to update the damage in seeldings
    updateSeedlingDamage(value: ShadeAreaModel): void {
        this.updateBatchValue = new BatchModel();
        // console.log(value);
        this.updateBatchValue.id = value.batchId;
        this.updateBatchValue.status = STATUS_SEEDLING;
        this.damageModal.show();
    }

    // show model popup to shift the seed to seasoning area (stock)
    updateStock(value: ShadeAreaModel): void {
        this.nurseryStockDetails.batchId = value.batchId;
        this.stockModal.show();
    }

    closeBatch(batch: BatchModel): void {
        this.batch = batch;
        this.batch.status = -1;
        this.batch.closedDate = moment(this.batch.closedDate, DATE_TIME_FORMAT);
        this.batchService.update(this.batch)
        .subscribe(
            data => {
                alert('Bacth Closed Successfully.');
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.closeBatchModal.hide();
    }

    createDamage(value: DamageModel): void {
        // this.damage = value;
        console.log(value);
        this.damage.batchId = value.id;
        this.damage.noOfQuantity = value.noOfQuantity;
        this.damage.date = moment(value.date, DATE_TIME_FORMAT);
        this.damage.descriptionId = value.descriptionId;
        this.damage.status = value.status;
        // console.log(this.damage);
        this.damageService.create(this.damage)
        .subscribe(data => {
                alert('Damage Created Successfully.');
                this.getBatchList();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.damageModal.hide();
    }

    createShadeArea(value: ShadeAreaModel): void {
        // this.shadeArea = value;
        // console.log(value);
        this.shadeArea.batchId = value.id;
        this.shadeArea.noOfSeedlings = value.noOfSeedlings;
        this.shadeArea.date = moment(value.date, DATE_TIME_FORMAT);
        // console.log(this.shadeArea);
        this.shadeAreaService.create(this.shadeArea)
        .subscribe(data => {
                alert('Successfully Moved to Shade Area.');
                this.updateBatchValue = value;
                // console.log(this.updateBatchValue);
                // If batch round is more then 0 increase the count or set 1 for a variable
                if (this.updateBatchValue.round > 0) {
                    // console.log('>');
                    this.updateBatchValue.round = this.updateBatchValue.round + 1;
                } else {
                    // console.log('<');
                    this.updateBatchValue.round = 1;
                }
                // console.log(this.updateBatchValue);
                // Update the Batch model using service
                this.batchService.update(this.updateBatchValue)
                .subscribe(res => {
                        this.getBatchList();
                    },
                    (res: HttpErrorResponse) => {
                        alert(res.error.fieldErrors[0].message);
                    }
                );
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
        this.shiftBatchModal.hide();
    }

    createStock(): void {
        this.batch = new BatchModel();
        // console.log(this.nurseryStockDetails);
        this.batchService.find(this.nurseryStockDetails.batchId)
        .subscribe(output => {
            // console.log(res.body);
            this.batch = output.body;
            this.nurseryStock.nurseryId = this.batch.nurseryId;
            this.nurseryStock.pickListVarietyId = this.batch.pickListVarietyId;
            this.nurseryStock.pickListCategoryId = this.batch.pickListCategoryId;
            this.nurseryStock.status = this.stockStatus;
            this.nurseryStockDetails.status = this.addStockStatus;
            // this.nurseryStock.nurseryStockDetails = [this.nurseryStockDetails];
            // console.log(this.nurseryStock);
            this.nurseryStockService.getNurseryCategoryStock(this.nurseryStock.nurseryId, this.nurseryStock.pickListCategoryId)
            .subscribe((res: HttpResponse<INurseryStock[]>) => {
                // console.log(res.body);
                // console.log(res.body.length);
                if (res.body.length > 0) {
                    this.nurseryStock = res.body[res.body.length - 1];
                    this.nurseryStock.currentQuantity = +this.nurseryStock.currentQuantity + +this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = +this.nurseryStock.addedQuantity + +this.nurseryStockDetails.quantity;
                    this.nurseryStockService.update(this.nurseryStock)
                    .subscribe(
                        data => {
                            // alert('Bacth Closed Successfully.');
                            this.nurseryStockDetails.nurseryStockId = data.body.id;
                            this.createNurseryStockDetails(this.nurseryStockDetails);
                        },
                        (err: HttpErrorResponse) => {
                            alert(err.error.fieldErrors[0].message);
                        }
                    );
                } else {
                    this.nurseryStock.currentQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStock.addedQuantity = this.nurseryStockDetails.quantity;
                    this.nurseryStockService.create(this.nurseryStock)
                    .subscribe(data => {
                            // console.log(data.body);
                            this.nurseryStockDetails.nurseryStockId = data.body.id;
                            this.createNurseryStockDetails(this.nurseryStockDetails);
                        },
                        (err: HttpErrorResponse) => {
                            alert(err.error.fieldErrors[0].message);
                        }
                    );
                }
            });
        });
    }

    createNurseryStockDetails(nurseryStockDetails): void {
        this.nurseryStockDetails = nurseryStockDetails;
        this.nurseryStockDetails.date = moment(this.nurseryStockDetails.date, DATE_TIME_FORMAT);
        this.nurseryStockDetailsService.create(this.nurseryStockDetails)
        .subscribe(data => {
                // console.log(data.body);
                alert('Successfully Moved To Seasoning Area.');
                this.stockModal.hide();
            },
            (res: HttpErrorResponse) => {
                alert(res.error.fieldErrors[0].message);
            }
        );
    }

    // Get the damage value based on batch id
    getParticularBatchDamage(batchId): void {
        this.damageRecordModal.show();
        // Get the list of damage
        this.damageService.getParticularBatchRecord(batchId).subscribe((res: HttpResponse<IDamage[]>) => {
            this.damages = res.body;
        });
    }

    // Get the shadeArea value based on batch id
    getParticularBatchShadeArea(batchId): void {
        this.shadeAreaRecordModal.show();
        // Get the list of shade area record
        this.shadeAreaService.getParticularBatchRecord(batchId).subscribe((res: HttpResponse<IDamage[]>) => {
            this.shadeAreas = res.body;
        });
    }

    set batchModel(batch: IBatch) {
        this.createdAt = moment(this.batch.createdAt).format(DATE_TIME_FORMAT);
        this.updatedAt = moment(this.batch.updatedAt).format(DATE_TIME_FORMAT);
    }
}
