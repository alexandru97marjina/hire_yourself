import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable()
export class IsAuthenticatedGuard implements CanActivate {
    constructor(public router: Router) {
    }

    canActivate(): boolean {
        if (!AuthHelper.getAuthenticated()) {
            this.router.navigate(['login']);
            return false;
        }

        return true;
    }
}
