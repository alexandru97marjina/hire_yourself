import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { HttpService } from './http.service';
import { UserInterface } from '@interfaces/user.interface';
import { throwError } from 'rxjs';
import { Api } from '@helpers/api.helper';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    readonly authApi = Api.Management;

    constructor(private httpService: HttpService) {
    }

    public login({ email, password }) {
        return this.httpService.post('/api/security/users/log', { email, password }, false).pipe(
            tap((data: UserInterface) => {
                AuthHelper.setMe(data);
                AuthHelper.setAuthenticated(true);
            }),
            catchError((error) => {
                console.log(error);
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
        return this.httpService.post(this.authApi.requestPassword, { email }, false);
    }

    public register(email: string, password: string) {
        return this.httpService.post(this.authApi.register, { email, password }, false);
    }
}
