import { Moment } from 'moment';

export interface IGodownPurchaseDetails {
    id?: number;
    quantity?: number;
    date?: Moment;
    price?: number;
    ownedBy?: string;
    vendorName?: string;
    vendorAddress?: string;
    vendorPhone?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
}

export class GodownPurchaseDetails implements IGodownPurchaseDetails {
    constructor(
        public id?: number,
        public quantity?: number,
        public date?: Moment,
        public price?: number,
        public ownedBy?: string,
        public vendorName?: string,
        public vendorAddress?: string,
        public vendorPhone?: number,
        public description?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
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

export class GodownPurchaseDetailsModel {
    id?: number;
    quantity?: number;
    date?: Moment;
    price?: number;
    ownedBy?: string;
    vendorName?: string;
    vendorAddress?: string;
    vendorPhone?: number;
    description?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    pickListVarietyPickListValue?: string;
    pickListVarietyId?: number;
    pickListCategoryPickListValue?: string;
    pickListCategoryId?: number;
    pickListQuantityTypePickListValue?: string;
    pickListQuantityTypeId?: number;
    godownName?: string;
    godownId?: number;
    pickList?: String;
    pickListId?: number;
}
