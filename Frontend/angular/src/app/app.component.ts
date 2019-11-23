import { Component, OnDestroy } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthHelper } from '@helpers/auth.helper';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnDestroy {
    title = 'angular';

    constructor(private translate: TranslateService) {
        translate.setDefaultLang('en');

        translate.use('en');
    }

    ngOnDestroy(): void {
        AuthHelper.setAuthenticated(false);
        AuthHelper.setMe(null);
    }

    isAuthenticated() {
        return AuthHelper.getAuthenticated();
    }
}
