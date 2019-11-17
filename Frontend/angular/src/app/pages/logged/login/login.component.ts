import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { Router } from '@angular/router';
import {AuthService} from '@services/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {catchError, tap} from 'rxjs/operators';
import { NotificationService } from '@services/notification.service';
import { of } from 'rxjs';

enum Page {
    login = 'login',
    resetPassword = 'resetPassword',
}
@Component({
    selector: 'app-my-posts',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    public form: FormGroup;
    public resetPasswordForm: FormGroup;
    public loading = false;
    public pages = Page;
    public page: Page = Page.login;
    public isSubmitted = false;

    constructor(
        private router: Router,
        private authService: AuthService,
        private fb: FormBuilder,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit(): void {
        this.initForm();
        this.initResetPasswordForm();
    }

    initForm() {
        this.form = this.fb.group({
            email: this.fb.control('', [Validators.required, Validators.email]),
            password: this.fb.control('', [Validators.required, Validators.minLength(6)])
        });
    }

    initResetPasswordForm() {
        this.resetPasswordForm = this.fb.group({
            email: this.fb.control('', [Validators.required, Validators.email])
        });
    }

    public login() {
        this.isSubmitted = true;
        if (this.form.invalid) {
            this.notificationService.error('Form is not correctly completed');
            return;
        }

        const credentials = this.form.value;
        this.authService.login(credentials).pipe(
            tap(() => { this.loading = true; }),
            catchError((error) => {
                this.notificationService.error(error);
                return of(null);
            })
        ).subscribe((data) => {
            this.loading = false;
            this.router.navigate(['/']);
        });
    }

    public resetPassword() {
        if (this.resetPasswordForm.invalid) {
            this.notificationService.error('Form is not correctly completed');
            return;
        }

        const credentials = this.form.value;
        this.authService.requestResetPassword(credentials).pipe(
            tap(() => { this.loading = true; }),
            catchError((error) => {
                this.notificationService.error(error);
                return of(null);
            })
        ).subscribe((data) => {
            this.page = this.pages.login;
            this.loading = false;
        });
    }

    isInvalid(controlName: string) {
        return this.isSubmitted && this.form.get(controlName).invalid;
    }

    changePage(page: Page) {
        this.page = page;
    }
}
