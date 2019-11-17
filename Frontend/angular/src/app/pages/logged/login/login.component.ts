import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { Router } from '@angular/router';
import {AuthService} from '@services/auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {catchError, tap} from 'rxjs/operators';
import { NotificationService } from '@services/notification.service';

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

    constructor(
        private router: Router,
        private authService: AuthService,
        private fb: FormBuilder,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit(): void {
        this.initForm();

        // this.router.navigate(['/']).then(() => {
        //     AuthHelper.setAuthenticated(true);
        //     AuthHelper.setMe({
        //         id: 0,
        //         email: 'email@example.com',
        //         first_name: 'Eugeniu',
        //         last_name: 'Nicolenco',
        //         address: 'Chisinau',
        //         phone: '+37360123456'
        //     });
        // });
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
        if (this.form.invalid) {
            // TODO: add notification
            return;
        }

        const credentials = this.form.value;
        this.authService.login(credentials).pipe(
            tap(() => { this.loading = true; }),
            catchError((error) => {
                // TODO add notification
                return null;
            })
        ).subscribe((data) => {
            this.loading = false;
            this.router.navigate(['/']);
        });
    }

    public resetPassword() {
        if (this.resetPasswordForm.invalid) {
            // TODO add notification
            return;
        }

        const credentials = this.form.value;
        this.authService.requestResetPassword(credentials).pipe(
            tap(() => { this.loading = true; }),
            catchError((error) => {
                // TODO add notification
                return null;
            })
        ).subscribe((data) => {
            this.page = this.pages.login;
            this.loading = false;
        });
    }
}
