import { Injectable } from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { HttpService } from './http.service';
import { User } from '@interfaces/user';
import { throwError } from 'rxjs';
import { Api } from '@helpers/api.helper';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    readonly authApi = Api.Management;

    constructor(private httpService: HttpService) {
    }

    public static getMe() {
        return JSON.parse(localStorage.getItem('me'));
    }

    public static setMe(user: User) {
        localStorage.setItem('me', (user ? JSON.stringify(user) : null));
    }


    public static getAuthenticated() {
        return Boolean(JSON.parse(localStorage.getItem('authenticated')));
    }

    public static setAuthenticated(value: boolean) {
        localStorage.setItem('authenticated', JSON.stringify(value));
    }

    public login({ email, password }) {
        return this.httpService.post('/login_check', { email, password }).pipe(
            tap((data: User) => {
                AuthService.setMe(data);
                AuthService.setAuthenticated(true);
            }),
            catchError((error) => {
                AuthService.setAuthenticated(false);
                return throwError(error);
            })
        );
    }

    public logout() {
        AuthService.setAuthenticated(false);
        AuthService.setMe(null);
    }

    public resetPassword(email: string, password: string) {
        return this.httpService.post(this.authApi.resetPassword, { email, password });
    }

    public requestResetPassword(email: string) {
        return this.httpService.post(this.authApi.requestPassword, { email });
    }

    public register(email: string, password: string) {
        return this.httpService.post(this.authApi.register, { email, password });
    }
}
