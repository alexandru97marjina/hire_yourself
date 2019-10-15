import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { PostsListComponent } from '@app/pages/posts-list/posts-list.component';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';
import { MyPostsComponent } from '@app/pages/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/favorites-posts/favorites-posts.component';


const routes: Routes = [
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
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
