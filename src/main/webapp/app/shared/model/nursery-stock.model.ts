import { Moment } from 'moment';
import { INurseryStockDetails } from 'app/shared/model//nursery-stock-details.model';

export interface INurseryStock {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
}

export class NurseryStock implements INurseryStock {
    constructor(
        public id?: number,
        public currentQuantity?: number,
        public addedQuantity?: number,
        public consumedQuantity?: number,
        public description?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public nurseryStockDetails?: INurseryStockDetails[],
        public nurseryNurseryName?: string,
        public nurseryId?: number,
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number
    ) {}
}

export class NurseryStockModel {
    id?: number;
    currentQuantity?: number;
    addedQuantity?: number;
    consumedQuantity?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    nurseryStockDetails?: INurseryStockDetails[];
    nurseryNurseryName?: string;
    nurseryId?: number;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
}

export const STATUS_ACTIVE = 1;
