import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { PostInterface } from '@interfaces/post.interface';
import { AuthHelper } from '@helpers/auth.helper';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { map, shareReplay, switchMap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';

@Injectable({
    providedIn: 'root'
})
export class PostService {

    readonly postApi = Api.Post;
    headerCounter: Subject<any> = new ReplaySubject();
    favoritesCount: Observable<any> = this.headerCounter.pipe(
        switchMap(() => this.getFavorites(AuthHelper.getMe().id)),
        map((response: ResponseInterface) => response.data.length)
    );
    listObserver: Subject<any> = new ReplaySubject();
    favoritesIds: Observable<any> = this.listObserver.pipe(
        switchMap(() => this.getFavorites(AuthHelper.getMe().id)),
        map((response: ResponseInterface) => {
           return response.data ? response.data.map(item => item.id) : [];
        }),
        shareReplay(1)
    );

    constructor(private httpService: HttpService) {
    }

    private static getRandomInt(max) {
        return Math.floor(Math.random() * Math.floor(max));
    }

    public static mapData(formData): PostInterface {
        delete formData.file;
        if (!formData.imagePath || !formData.imagePath.includes('picsum')) {
            formData.imagePath = `https://picsum.photos/id/${this.getRandomInt(500)}/286/180`;
        }

        const time = new Date().getTime();
        return {
            ...formData,
            dateCreated: time,
            active: true,
            minExperience: 1,
            maxExperience: 1,
            jobPosition: 'Developer',
            dateExpired: time,
            dateUpdated: time,
            userId: AuthHelper.getMe().id,
        } as PostInterface;
    }

    public refreshCount() {
        this.headerCounter.next();
    }

    public refreshList() {
        this.listObserver.next();
        this.refreshCount();
    }

    public getPost(id: number) {
        return this.httpService.get(this.postApi.getOne(id));
    }

    public getList() {
        const images = [
            'https://res.cloudinary.com/dvspkouqw/image/upload/v1593358313/Webp.net-resizeimage_3_bnspcw.jpg',
            'https://res.cloudinary.com/dvspkouqw/image/upload/v1593358313/Webp.net-resizeimage_2_c0di3d.jpg',
            'https://res.cloudinary.com/dvspkouqw/image/upload/v1593358313/Webp.net-resizeimage_n27az3.jpg',
            'https://res.cloudinary.com/dvspkouqw/image/upload/v1593358313/Webp.net-resizeimage_4_fu4kwx.jpg',
            'https://res.cloudinary.com/dvspkouqw/image/upload/v1593358313/Webp.net-resizeimage_1_ha8w7w.jpg',
        ];

        return this.httpService.get(this.postApi.getList).pipe(
            map((data: PostInterface[]) => {
                if (data && Array.isArray(data)) {
                    data.forEach(item => {
                        item.imagePath = images[PostService.getRandomInt(5)];
                    });
                }

                return data;
            })
        );
    }

    public updatePost(id: number, post: PostInterface) {
        return this.httpService.put(this.postApi.update(id), post);
    }

    public createPost(post: PostInterface) {
        return this.httpService.post(this.postApi.create, post);
    }

    public deletePost(post: PostInterface) {
        return this.httpService.delete(this.postApi.delete(post.id));
    }

    public applyToPost(postId: number, userId: number) {
        return this.httpService.post(this.postApi.apply(postId, userId), {});
    }

    public acceptPost(postId: number, userId: number) {
        return this.httpService.post(this.postApi.accept(postId, userId), {});
    }

    public addToFavorites(userId: number, postId: number) {
        return this.httpService.get(this.postApi.addToFavorite(userId, postId));
    }

    public deleteFavorite(userId: number, postId: number) {
        return this.httpService.delete(this.postApi.deleteFavorite(userId, postId));
    }

    public getFavorites(userId: number) {
        return this.httpService.get(this.postApi.getFavorites(userId));
    }

    public getUserCreatedPosts(userId: number) {
        return this.httpService.get(this.postApi.userCreatedPosts(userId));
    }

    public getUserSubscribedPosts(userId: number) {
        return this.httpService.get(this.postApi.userSubscribedPosts(userId));
    }
}
