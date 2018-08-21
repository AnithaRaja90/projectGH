import { Moment } from 'moment';

export interface IGodownStockDetails {
    id?: number;
    date?: Moment;
    quantity?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    godownStockId?: number;
}

export class GodownStockDetails implements IGodownStockDetails {
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
        public godownStockId?: number
    ) {}
}
