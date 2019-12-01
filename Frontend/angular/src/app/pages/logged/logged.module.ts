import { NgModule } from '@angular/core';
import { PostsListComponent } from '@app/pages/logged/posts-list/posts-list.component';
import { PostComponent } from '@components/post/post.component';
import { MyPostsComponent } from '@app/pages/logged/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/logged/favorites-posts/favorites-posts.component';
import { PostFormComponent } from '@components/post-form/post-form.component';
import { SharedModule } from '@app/shared.module';
import { LoggedRoutingModule } from '@app/pages/logged/logged-routing.module';
import { TranslateModule } from '@ngx-translate/core';
import { UsersComponent } from '@app/pages/logged/users/users.component';
import { UserEditComponent } from '@components/user-edit/user-edit.component';
import { UserShowComponent } from '@components/user-show/user-show.component';
import { PostShowComponent } from '@components/post-show/post-show.component';

@NgModule({
    declarations: [
        PostsListComponent,
        PostComponent,
        PostFormComponent,
        MyPostsComponent,
        FavoritesPostsComponent,
        UsersComponent,
        UserShowComponent,
        PostShowComponent,
        UserEditComponent,
    ],
    imports: [
        SharedModule,
        LoggedRoutingModule,
        TranslateModule,
    ]
})
export class LoggedModule {
}
