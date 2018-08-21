import { Moment } from 'moment';
import { INursery } from 'app/shared/model//nursery.model';

export interface ISector {
    id?: number;
    sectorName?: string;
    sectorAddress?: string;
    sectorIncharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    nurserys?: INursery[];
    zonalZoneName?: string;
    zonalId?: number;
}

export class Sector implements ISector {
    constructor(
        public id?: number,
        public sectorName?: string,
        public sectorAddress?: string,
        public sectorIncharge?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public nurserys?: INursery[],
        public zonalZoneName?: string,
        public zonalId?: number
    ) {}
}

export class SectorModel {
    id?: number;
    sectorName?: string;
    sectorAddress?: string;
    sectorIncharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    zonalZoneName?: string;
    zonalId?: number;
    nursery?: INursery[];
}
