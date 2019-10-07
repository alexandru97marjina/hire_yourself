import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { throwError } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient) {
    }


    public get(apiEndPoint: string, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthService.getAuthenticated()) {
            return throwError('not authenticated');
        }

        return this.http.get(environment.apiRoute + apiEndPoint);
    }

    public post(apiEndPoint: string, body: object, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthService.getAuthenticated()) {
            return throwError('not authenticated');
        }

        return this.http.post(environment.apiRoute + apiEndPoint, body);
    }

    public put(apiEndPoint: string, body: object, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthService.getAuthenticated()) {
            return throwError('not authenticated');
        }

        return this.http.put(environment.apiRoute + apiEndPoint, body);
    }

    public delete(apiEndPoint: string, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthService.getAuthenticated()) {
            return throwError('not authenticated');
        }

        return this.http.delete(environment.apiRoute + apiEndPoint);
    }

}
