import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { UserInterface } from '@interfaces/user.interface';
import { Router } from '@angular/router';
import { PostService } from '@services/post.service';
import { catchError } from 'rxjs/operators';
import { of } from 'rxjs';
import { PostInterface } from '@interfaces/post.interface';
import { ResponseInterface } from '@interfaces/response.interface';
import { EmptyDataResponse } from '@app/factories/emptyDataResponse';

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent implements OnInit {

    public user: UserInterface = null;
    public favoritesCount = 0;

    constructor(
        private router: Router,
        private postService: PostService,
    ) {
    }

    ngOnInit(): void {
        this.user = AuthHelper.getMe();
        this.initFavoritesCount();
    }

    initFavoritesCount() {
        this.postService.getFavorites(this.user.id)
            .pipe(
                catchError(error => {
                    if (error.hasOwnProperty('message')) {
                        // TODO : add notification
                    }

                    return of(new EmptyDataResponse());
                })
            )
            .subscribe((data: ResponseInterface) => {
                    this.favoritesCount = data.data.length;
            });
    }

    logout() {
        this.router.navigate(['/public']).then(() => {
            AuthHelper.setAuthenticated(false);
            AuthHelper.setMe(null);
        });
    }
}
