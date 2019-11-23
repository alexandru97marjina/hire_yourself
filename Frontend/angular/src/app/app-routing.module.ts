import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostsListComponent } from '@app/pages/logged/posts-list/posts-list.component';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';
import { MyPostsComponent } from '@app/pages/logged/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/logged/favorites-posts/favorites-posts.component';
import { LoginComponent } from '@app/pages/public/login/login.component';
import { PublicComponent } from '@app/pages/public/public.component';


const routes: Routes = [
    {
        path: '',
        redirectTo: 'posts',
        pathMatch: 'full'
    },
    {
        path: 'public',
        component: PublicComponent
    },
    {
        path: 'posts',
        component: PostsListComponent,
        canActivate: [IsAuthenticatedGuard]
    },
    {
        path: 'my-posts',
        component: MyPostsComponent,
        canActivate: [IsAuthenticatedGuard]
    },
    {
        path: 'favorites',
        component: FavoritesPostsComponent,
        canActivate: [IsAuthenticatedGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: '**',
        redirectTo: '/',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
