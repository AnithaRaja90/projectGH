import { Moment } from 'moment';
import { IGodownStockDetails } from 'app/shared/model//godown-stock-details.model';

export interface IGodownStock {
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
    godownStockDetails?: IGodownStockDetails[];
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
}

export class GodownStock implements IGodownStock {
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
        public godownStockDetails?: IGodownStockDetails[],
        public pickListVarietyPickListValue?: string,
        public pickListVarietyId?: number,
        public pickListCategoryPickListValue?: string,
        public pickListCategoryId?: number,
        public pickListQuantityTypePickListValue?: string,
        public pickListQuantityTypeId?: number,
        public godownName?: string,
        public godownId?: number
    ) {}
}
