import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';
import { AuthHelper } from '@helpers/auth.helper';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent {
    title = 'angular';

    constructor(translate: TranslateService) {
        translate.setDefaultLang('en');

        translate.use('en');
    }

    isAuthenticated() {
        return AuthHelper.getAuthenticated();
    }
}
