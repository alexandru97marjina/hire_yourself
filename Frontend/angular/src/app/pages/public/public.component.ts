import { Component } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-public',
    templateUrl: './public.component.html',
    styleUrls: ['./public.component.scss']
})
export class PublicComponent {
    title = 'angular';

    constructor(translate: TranslateService) {
        translate.setDefaultLang('en');

        translate.use('en');
    }
}
