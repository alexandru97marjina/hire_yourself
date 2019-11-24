import { Component, OnInit } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';
import { UserInterface } from '@interfaces/user.interface';
import { Router } from '@angular/router';
import { PostService } from '@services/post.service';
import { Observable } from 'rxjs';

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent implements OnInit {

    public user: UserInterface = null;
    public favorites$: Observable<number>;

    constructor(
        private router: Router,
        private postService: PostService,
    ) {
    }

    ngOnInit(): void {
        this.user = AuthHelper.getMe();
        this.postService.refreshCount();
        this.favorites$ = this.postService.favoritesCount;
    }

    logout() {
        AuthHelper.setAuthenticated(false);
        AuthHelper.setMe(null);
        this.router.navigate(['/public/login']).then(() => {
            AuthHelper.setAuthenticated(false);
            AuthHelper.setMe(null);
        });
    }
}
