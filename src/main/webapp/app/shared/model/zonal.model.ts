import { Moment } from 'moment';
import { ISector } from 'app/shared/model//sector.model';

export interface IZonal {
    id?: number;
    zoneName?: string;
    zoneAddress?: string;
    zoneIncharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    sectors?: ISector[];
}

export class Zonal implements IZonal {
    constructor(
        public id?: number,
        public zoneName?: string,
        public zoneAddress?: string,
        public zoneIncharge?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public sectors?: ISector[]
    ) {}
}

export class ZonalModel {
    id?: number;
    zoneName?: string;
    zoneAddress?: string;
    zoneIncharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    sectors?: ISector[];
}
