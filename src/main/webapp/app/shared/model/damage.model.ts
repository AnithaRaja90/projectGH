import { Moment } from 'moment';

export interface IDamage {
    id?: number;
    noOfQuantity?: number;
    date?: Moment;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    batchBatchName?: string;
    batchId?: number;
    descriptionPickListValue?: string;
    descriptionId?: number;
}

export class Damage implements IDamage {
    constructor(
        public id?: number,
        public noOfQuantity?: number,
        public date?: Moment,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public batchBatchName?: string,
        public batchId?: number,
        public descriptionPickListValue?: string,
        public descriptionId?: number
    ) {}
}

export class DamageModel {
    id?: number;
    noOfQuantity?: number;
    date?: Moment;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    batchBatchName?: string;
    batchId?: number;
    descriptionPickListValue?: string;
    descriptionId?: number;
    pickListDescriptionId?: number;
}

export const STATUS_SEEDS = 1;
export const STATUS_SEEDLING = 2;
export const STATUS_SAPLING = 3;
export const STATUS_DISTRIBUTION = 4;
