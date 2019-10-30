import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { Router } from '@angular/router';

@Component({
    selector: 'app-my-posts',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    constructor(private router: Router) {
    }

    ngOnInit(): void {
        this.router.navigate(['/']).then(() => {
            AuthHelper.setAuthenticated(true);
            AuthHelper.setMe({
                id: 0,
                email: 'email@example.com',
                first_name: 'Eugeniu',
                last_name: 'Nicolenco',
                address: 'Chisinau',
                phone: '+37360123456'
            });
        });
    }
}
