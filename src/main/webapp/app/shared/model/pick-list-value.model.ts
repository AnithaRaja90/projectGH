import { Moment } from 'moment';
import { IPickListValue } from 'app/shared/model//pick-list-value.model';
import { IBatch } from 'app/shared/model//batch.model';
import { INurseryStock } from 'app/shared/model//nursery-stock.model';
import { IGodownPurchaseDetails } from 'app/shared/model//godown-purchase-details.model';
import { IGodownStock } from 'app/shared/model//godown-stock.model';
import { INursery } from 'app/shared/model//nursery.model';

export interface IPickListValue {
    id?: number;
    pickListValue?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    selfIds?: IPickListValue[];
    varietys?: IBatch[];
    categorys?: IBatch[];
    nurseryStockVarietys?: INurseryStock[];
    nurseryStockCategorys?: INurseryStock[];
    godownPurchaseVarietys?: IGodownPurchaseDetails[];
    godownPurchaseCategorys?: IGodownPurchaseDetails[];
    godownPurchaseQuantityTypes?: IGodownPurchaseDetails[];
    godownStockVarietys?: IGodownStock[];
    godownStockCategorys?: IGodownStock[];
    godownStockQuantityTypes?: IGodownStock[];
    pickListPickListName?: string;
    pickListId?: number;
    pickValuePickListValue?: string;
    pickValueId?: number;
    nurserys?: INursery[];
    batchQuantityTypes?: IBatch[];
}

export class PickListValue implements IPickListValue {
    constructor(
        public id?: number,
        public pickListValue?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public selfIds?: IPickListValue[],
        public varietys?: IBatch[],
        public categorys?: IBatch[],
        public nurseryStockVarietys?: INurseryStock[],
        public nurseryStockCategorys?: INurseryStock[],
        public godownPurchaseVarietys?: IGodownPurchaseDetails[],
        public godownPurchaseCategorys?: IGodownPurchaseDetails[],
        public godownPurchaseQuantityTypes?: IGodownPurchaseDetails[],
        public godownStockVarietys?: IGodownStock[],
        public godownStockCategorys?: IGodownStock[],
        public godownStockQuantityTypes?: IGodownStock[],
        public pickListPickListName?: string,
        public pickListId?: number,
        public pickValuePickListValue?: string,
        public pickValueId?: number,
        public nurserys?: INursery[],
        public batchQuantityTypes?: IBatch[]
    ) {}
}

export class PickListValueModel {
    id?: number;
    pickListValue?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    selfIds?: IPickListValue[];
    varietys?: IBatch[];
    categorys?: IBatch[];
    pickListPickListName?: string;
    nurseryStockVarietys?: INurseryStock[];
    nurseryStockCategorys?: INurseryStock[];
    godownPurchaseVarietys?: IGodownPurchaseDetails[];
    godownPurchaseCategorys?: IGodownPurchaseDetails[];
    godownPurchaseQuantityTypes?: IGodownPurchaseDetails[];
    godownStockVarietys?: IGodownStock[];
    godownStockCategorys?: IGodownStock[];
    godownStockQuantityTypes?: IGodownStock[];
    pickListId?: number;
    pickValuePickListValue?: string;
    pickValueId?: number;
    nurserys?: INursery[];
    batchQuantityTypes?: IBatch[];
    subChildValue?: string;
}
