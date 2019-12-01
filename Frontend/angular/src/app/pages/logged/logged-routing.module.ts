import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostsListComponent } from '@app/pages/logged/posts-list/posts-list.component';
import { MyPostsComponent } from '@app/pages/logged/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/logged/favorites-posts/favorites-posts.component';
import { UsersComponent } from '@app/pages/logged/users/users.component';
import { UserEditComponent } from '@components/user-edit/user-edit.component';


const routes: Routes = [
    {
        path: '',
        redirectTo: 'posts',
        pathMatch: 'full'
    },
    {
        path: 'posts',
        component: PostsListComponent
    },
    {
        path: 'my-posts',
        component: MyPostsComponent
    },
    {
        path: 'favorites',
        component: FavoritesPostsComponent
    },
    {
        path: 'users',
        children: [
            {
                path: '',
                component: UsersComponent
            },
            {
                path: 'edit/:id',
                component: UserEditComponent
            }
        ]
    },
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LoggedRoutingModule {
}
