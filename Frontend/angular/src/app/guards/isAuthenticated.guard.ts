import { Injectable } from '@angular/core';
import { Router, CanActivate } from '@angular/router';
import { AuthService } from '@services/auth.service';

@Injectable()
export class IsAuthenticatedGuard implements CanActivate {
    constructor(public router: Router) {
    }

    canActivate(): boolean {
        if (!AuthService.getAuthenticated()) {
            this.router.navigate(['login']);
            return false;
        }

        return true;
    }
}
