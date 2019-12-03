import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { HttpService } from './http.service';
import { UserInterface } from '@interfaces/user.interface';
import { throwError } from 'rxjs';
import { Api, ApiHelper } from '@helpers/api.helper';
import { AuthHelper } from '@helpers/auth.helper';
import { ResponseInterface } from '@interfaces/response.interface';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    readonly authApi = Api.Management;

    constructor(private httpService: HttpService) {
    }

    public login({ email, password }) {
        return this.httpService.post(this.authApi.login, { email, password }, false).pipe(
            tap((response: ResponseInterface) => {
                AuthHelper.setMe(response.data as UserInterface);
                AuthHelper.setAuthenticated(true);
            }),
            catchError((error) => {
                AuthHelper.setAuthenticated(false);
                if (error && error.hasOwnProperty('message')) {
                    return throwError(error.message);
                }

                return throwError(error);
            })
        );
    }

    public logout() {
        AuthHelper.setAuthenticated(false);
        AuthHelper.setMe(null);
    }

    public resetPassword(email: string, password: string) {
        return this.httpService.post(this.authApi.resetPassword, { email, password }, false);
    }

    public requestResetPassword(email: string) {
        console.log(email);
        const api = ApiHelper.queryParams({email});
        return this.httpService.get(this.authApi.requestPassword + api, false);
    }

    public register(email: string, password: string) {
        return this.httpService.post(this.authApi.register, { email, password }, false);
    }
}
