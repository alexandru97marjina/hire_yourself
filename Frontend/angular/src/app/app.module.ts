import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppHeaderComponent } from '@components/header/app-header.component';
import { NgbDropdownModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { PostsListComponent } from '@app/pages/logged/posts-list/posts-list.component';
import { PostComponent } from '@components/post/post.component';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';
import { MyPostsComponent } from '@app/pages/logged/my-posts/my-posts.component';
import { FavoritesPostsComponent } from '@app/pages/logged/favorites-posts/favorites-posts.component';
import { LengthPipe } from '@app/pipes/length.pipe';
import { PostFormComponent } from '@components/post-form/post-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from '@app/pages/logged/login/login.component';
import { IsAuthenticatedDirective } from '@app/directives/isAuthenticated.directive';
import { LoggedComponent } from '@app/pages/logged/logged.component';
import { PublicComponent } from '@app/pages/public/public.component';
import {ToastComponent} from '@components/toast/toast.component';

export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
    declarations: [
        AppComponent,
        AppHeaderComponent,
        PostsListComponent,
        PostComponent,
        PostFormComponent,
        MyPostsComponent,
        FavoritesPostsComponent,
        LengthPipe,
        LoginComponent,
        IsAuthenticatedDirective,
        LoggedComponent,
        PublicComponent,
        ToastComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule,
        NgbModule,
        NgbDropdownModule,
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: (HttpLoaderFactory),
                deps: [HttpClient]
            }
        }),
        ReactiveFormsModule
    ],
    providers: [
        HttpClient,
        IsAuthenticatedGuard,
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
