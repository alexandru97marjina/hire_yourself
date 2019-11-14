import {Injectable, TemplateRef} from '@angular/core';
import { catchError, tap } from 'rxjs/operators';
import { HttpService } from './http.service';
import { UserInterface } from '@interfaces/user.interface';
import { throwError } from 'rxjs';
import { Api } from '@helpers/api.helper';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    constructor() {
    }

    private toasts: any[] = [];

    private show(textOrTpl: string | TemplateRef<any>, options: any = {}) {
        this.toasts.push({ textOrTpl, ...options });
    }

    remove(toast) {
        this.toasts = this.toasts.filter(t => t !== toast);
    }

    success(message: string, delay = 5000) {
        this.show(message, {classname: 'bg-success text-light toast show', delay});
    }

    error(message: string, delay = 5000) {
        this.show(message,  { classname: 'bg-danger text-light toast show', delay});
    }

    info(message: string, delay = 5000) {
        this.show(message, {classname: 'toast show', delay});
    }

    get notifications() {
        return this.toasts;
    }
}
