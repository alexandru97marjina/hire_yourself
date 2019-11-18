import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@environments/environment';
import { throwError } from 'rxjs';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient) {
    }

    public get(apiEndPoint: string, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.get(environment.apiRoute + apiEndPoint, { headers });
    }

    public post(apiEndPoint: string, body: object, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.post(environment.apiRoute + apiEndPoint, body, { headers });
    }

    public put(apiEndPoint: string, body: object, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.put(environment.apiRoute + apiEndPoint, body, { headers });
    }

    public delete(apiEndPoint: string, checkAuthenticated = true) {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.delete(environment.apiRoute + apiEndPoint, { headers });
    }

}
