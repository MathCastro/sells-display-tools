import { User } from '../model/UserBO';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UserService {
    constructor(private http: HttpClient) { }

    baseUrl = "http://localhost:8080";

    getUsers(): Observable<User[]>{
        return this.http.get<User[]>(this.baseUrl + '/user')
    }
}