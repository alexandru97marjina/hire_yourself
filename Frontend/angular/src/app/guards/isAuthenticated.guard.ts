import { Injectable } from '@angular/core';
import { Router, CanLoad } from '@angular/router';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable()
export class IsAuthenticatedGuard implements CanLoad {
    constructor(public router: Router) {
    }

    canLoad(): boolean {
        if (!AuthHelper.getAuthenticated()) {
            this.router.navigate(['/', 'public', 'login']);
            return false;
        }

        return true;
    }
}
