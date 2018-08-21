import { Moment } from 'moment';
import { IPickListValue } from 'app/shared/model//pick-list-value.model';

export interface IPickList {
    id?: number;
    pickListName?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    pickListValues?: IPickListValue[];
}

export class PickList implements IPickList {
    constructor(
        public id?: number,
        public pickListName?: string,
        public status?: number,
        public createdBy?: number,
        public modifiedBy?: number,
        public createdAt?: Moment,
        public updatedAt?: Moment,
        public pickListValues?: IPickListValue[]
    ) {}
}

export class PickListModel {
    id?: number;
    pickListName?: string;
    status?: number;
    createdBy?: number;
    modifiedBy?: number;
    createdAt?: Moment;
    updatedAt?: Moment;
    pickListValues?: IPickListValue[];
}
