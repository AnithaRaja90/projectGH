import { Moment } from 'moment';
import { IDamage } from 'app/shared/model//damage.model';
import { IShadeArea } from 'app/shared/model//shade-area.model';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';

export interface IBatch {
    id?: number;
    batchNo?: string;
    batchName?: string;
    quantity?: number;
    motherBed?: string;
    showingType?: number;
    sowingDate?: Moment;
    closedDate?: Moment;
    round?: number;
    remarks?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    damages?: IDamage[];
    shadeAreas?: IShadeArea[];
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    quantityTypePickListValue?: string;
    quantityTypeId?: number;
}

export class Batch implements IBatch {
    constructor(
        public id?: number,
        public batchNo?: string,
        public batchName?: string,
        public quantity?: number,
        public motherBed?: string,
        public showingType?: number,
        public sowingDate?: Moment,
        public closedDate?: Moment,
        public round?: number,
        public remarks?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public damages?: IDamage[],
        public shadeAreas?: IShadeArea[],
        public nurseryStockDetails?: INurseryStockDetails[],
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public quantityTypePickListValue?: string,
        public quantityTypeId?: number
    ) {}
}

export class BatchModel {
    id?: number;
    batchNo?: string;
    batchName?: string;
    quantity?: number;
    motherBed?: string;
    showingType?: number;
    sowingDate?: Moment;
    closedDate?: Moment;
    round?: number;
    remarks?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    damages?: IDamage[];
    shadeAreas?: IShadeArea[];
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    quantityTypePickListValue?: string;
    quantityTypeId?: number;
    pickListQuantityId?: number;
}
