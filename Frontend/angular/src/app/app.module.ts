import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from '@components/header/app-header.component';
import { NgbDropdownModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { PostsListComponent } from '@app/pages/posts-list/posts-list.component';
import { PostComponent } from '@components/post/post.component';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';
import { MyPostsComponent } from '@app/pages/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/favorites-posts/favorites-posts.component';

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
    declarations: [
        AppComponent,
        AppHeaderComponent,
        PostsListComponent,
        PostComponent,
        MyPostsComponent,
        FavoritesPostsComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        NgbModule,
        NgbDropdownModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (HttpLoaderFactory),
                deps: [HttpClient]
            }
        })
    ],
    providers: [
        HttpClient,
        IsAuthenticatedGuard,
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
