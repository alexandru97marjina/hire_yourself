import { Component } from '@angular/core';
import { AuthService } from '@services/auth.service';

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent {

    authenticate() {
        AuthService.setAuthenticated(true);
    }

    isAuthenticated() {
        return AuthService.getAuthenticated();
    }

    logout() {
        AuthService.setAuthenticated(false);
    }
}
