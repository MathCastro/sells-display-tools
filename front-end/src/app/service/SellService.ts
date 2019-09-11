import { Sell } from '../model/SellBO';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SellPaginator } from '../model/SellPaginator';

@Injectable()
export class SellService {
    constructor(private http: HttpClient) { }

    baseUrl = "http://localhost:8080";

    headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });

    getSells(pageSize?: number, pageNo?: number): Observable<SellPaginator> {
        const size = pageSize == null ? 5 : pageSize
        const number = pageNo == null ? 0 : pageNo
        const params = new HttpParams()
            .set('pageNo', number.toString())
            .set('pageSize', size.toString());
        return this.http.get<SellPaginator>(this.baseUrl + '/sell' ,{params});
    }

    deleteSell(id: number): void {
        this.http.delete(this.baseUrl + '/sell/' + id)
    }

    filterSells(value?: string, pageSize?: number, pageNo?: number): Observable<SellPaginator> {
        const size = pageSize == null ? 5 : pageSize
        const number = pageNo == null ? 0 : pageNo
        const params = new HttpParams()
            .set('pageNo', number.toString())
            .set('pageSize', size.toString())
            .set('value', value);
        return this.http.get<SellPaginator>(this.baseUrl + '/sell/filter-sells' ,{params});
    }

    // filterSells(value: string): Observable<Sell[]> {
    //     var argument = {
    //         filter: value
    //     }
    //     return this.http.post<Sell[]>(this.baseUrl + '/sell/filter', argument)
    // }

    createSell(sell: Sell): Observable<Sell> {
        return this.http.post<Sell>(this.baseUrl + '/sell', JSON.stringify(sell), {headers: this.headers})
    }
}