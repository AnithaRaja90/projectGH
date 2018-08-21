import { Moment } from 'moment';

export interface INurseryStockDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
}

export class NurseryStockDetails implements INurseryStockDetails {
    constructor(
        public id?: number,
        public date?: Moment,
        public quantity?: number,
        public description?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public batchBatchName?: string,
        public batchId?: number,
        public nurseryStockId?: number
    ) {}
}

export class NurseryStockDetailsModel {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    batchBatchName?: string;
    batchId?: number;
    nurseryStockId?: number;
}

export const STATUS_ADD = 1;
export const STATUS_CONSUME = 2;
