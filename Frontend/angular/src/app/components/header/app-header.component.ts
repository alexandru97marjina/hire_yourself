import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { UserInterface } from '@interfaces/user.interface';

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent implements OnInit {

    public user: UserInterface = null;

    ngOnInit(): void {
        this.user = AuthHelper.getMe();
    }

    authenticate() {
        const user: UserInterface = {
            id: 0,
            email: 'email@example.com',
            first_name: 'Eugeniu',
            last_name: 'Nicolenco',
            address: 'Chisinau',
            phone: '+37360123456'
        };
        AuthHelper.setAuthenticated(true);
        AuthHelper.setMe(user);
    }

    isAuthenticated() {
        return AuthHelper.getAuthenticated();
    }

    logout() {
        AuthHelper.setAuthenticated(false);
        AuthHelper.setMe(null);
    }
}
