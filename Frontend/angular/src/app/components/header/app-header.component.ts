import { Component } from '@angular/core';
import { AuthService } from '@services/auth.service';
import { AuthHelper } from '@helpers/auth.helper';

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent {

    authenticate() {
        AuthHelper.setAuthenticated(true);
    }

    isAuthenticated() {
        return AuthHelper.getAuthenticated();
    }

    logout() {
        AuthHelper.setAuthenticated(false);
    }
}
