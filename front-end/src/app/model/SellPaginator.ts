import { Sell } from './SellBO';

export interface SellPaginator {
    totalPages: number,
    totalElements: number,
    last: boolean,
    size: number,
    number: number,
    content: Sell[]
}