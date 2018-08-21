import { Moment } from 'moment';
import { IGodownPurchaseDetails } from 'app/shared/model//godown-purchase-details.model';
import { IGodownStock } from 'app/shared/model//godown-stock.model';

export interface IGodown {
    id?: number;
    name?: string;
    address?: string;
    incharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    godownStocks?: IGodownStock[];
}

export class Godown implements IGodown {
    constructor(
        public id?: number,
        public name?: string,
        public address?: string,
        public incharge?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public godownPurchaseDetails?: IGodownPurchaseDetails[],
        public godownStocks?: IGodownStock[]
    ) {}
}

export class GodownModel {
    id?: number;
    name?: string;
    address?: string;
    incharge?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    godownPurchaseDetails?: IGodownPurchaseDetails[];
    godownStocks?: IGodownStock[];
}
