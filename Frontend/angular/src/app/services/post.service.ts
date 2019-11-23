import { Injectable } from '@angular/core';
import { HttpService } from './http.service';
import { Api } from '@helpers/api.helper';
import { PostInterface } from '@interfaces/post.interface';
import { AuthHelper } from '@helpers/auth.helper';

@Injectable({
    providedIn: 'root'
})
export class PostService {

    readonly postApi = Api.Post;

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

        return {
            ...formData,
            dateCreated: '2016-03-22 00:00:00',
            active: true,
            minExperience: 1,
            maxExperience: 1,
            jobPosition: 'Developer',
            dateExpired: '2016-05-22 00:00:00',
            dateUpdated: '2016-03-22 00:00:00',
            userId: AuthHelper.getMe().id,
        } as PostInterface;
    }

    public getPost(id: number) {
        return this.httpService.get(this.postApi.getOne(id));
    }

    public getList() {
        return this.httpService.get(this.postApi.getList);
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

    public addToFavorites(postId: number, userId: number) {
        return this.httpService.post(this.postApi.addToFavorite(postId, userId), {});
    }

    public deleteFavorite(postId: number, userId: number) {
        return this.httpService.post(this.postApi.deleteFavorite(postId, userId), {});
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
