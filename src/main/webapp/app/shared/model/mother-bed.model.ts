import { Moment } from 'moment';

export interface IMotherBed {
    id?: number;
    value?: number;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    nurseryNurseryName?: string;
    nurseryId?: number;
}

export class MotherBed implements IMotherBed {
    constructor(
        public id?: number,
        public value?: number,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public nurseryNurseryName?: string,
        public nurseryId?: number
    ) {}
}

export class MotherBedModel {
    id?: number;
    value?: number;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    nurseryNurseryName?: string;
    nurseryId?: number;
}
