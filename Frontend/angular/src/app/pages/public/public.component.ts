import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthHelper } from '@helpers/auth.helper';
import { Router } from '@angular/router';

@Component({
    selector: 'app-public',
    templateUrl: './public.component.html',
    styleUrls: ['./public.component.scss']
})
export class PublicComponent implements OnInit {

    constructor(
        private translate: TranslateService,
        private router: Router,
    ) {
        translate.setDefaultLang('en');

        translate.use('en');
    }

    ngOnInit(): void {
        if (!AuthHelper.getAuthenticated()) {
            this.router.navigate(['/', 'login']);
        }
    }
}
