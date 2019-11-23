import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Observable, throwError } from 'rxjs';
import { AuthHelper } from '@helpers/auth.helper';
import { catchError } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class HttpService {

    constructor(private http: HttpClient) {
    }

    public get(apiEndPoint: string, checkAuthenticated = true): Observable<any> {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.get(environment.apiRoute + apiEndPoint, { headers }).pipe(
            catchError((error) => {
                if (error && error.hasOwnProperty('error')) {
                    return throwError(error.error);
                }

                return throwError(error);
            })
        ) as Observable<any>;
    }

    public post(apiEndPoint: string, body: object, checkAuthenticated = true): Observable<any> {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.post(environment.apiRoute + apiEndPoint, body, { headers }).pipe(
            catchError((error) => {
                if (error && error.hasOwnProperty('error')) {
                    return throwError(error.error);
                }

                return throwError(error);
            })
        ) as Observable<any>;
    }

    public put(apiEndPoint: string, body: object, checkAuthenticated = true): Observable<any> {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.put(environment.apiRoute + apiEndPoint, body, { headers }).pipe(
            catchError((error) => {
                if (error && error.hasOwnProperty('error')) {
                    return throwError(error.error);
                }

                return throwError(error);
            })
        ) as Observable<any>;
    }

    public delete(apiEndPoint: string, checkAuthenticated = true): Observable<any> {
        if (checkAuthenticated && !AuthHelper.getAuthenticated()) {
            return throwError('not authenticated');
        }

        const headers = (new HttpHeaders()).set('Token', 'hitProj');
        headers.set('Content-Type', 'application/json');

        return this.http.delete(environment.apiRoute + apiEndPoint, { headers }).pipe(
            catchError((error) => {
                console.log(error);
                if (error && error.hasOwnProperty('error')) {
                    return throwError(error.error);
                }

                return throwError(error);
            })
        ) as Observable<any>;
    }

}
