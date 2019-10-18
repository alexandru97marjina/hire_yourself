import { Component, OnInit } from '@angular/core';
import { PostService } from '@services/post.service';
import { ReplaySubject, Subject } from 'rxjs';
import { map, switchMap, tap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';

@Component({
    selector: 'app-posts-list',
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {

    public postsObserver: Subject<any> = new ReplaySubject(1);
    public posts$ = this.postsObserver.pipe(
        switchMap(() => this.postService.getList()),
        map( (response: ResponseInterface) => response.data),
        tap(console.log)
    );

    constructor(
        protected postService: PostService,
    ) {
    }

    ngOnInit() {
        this.postsObserver.next('');
    }

    // numbers = [1, 1, 1, 1, 1, 1, 1, 1, ];
    // public POSTS: Post[] = [
    //     {
    //         id: 0,
    //         title: 'test1',
    //         imagePath: 'https://picsum.photos/286/180',
    //         description: 'A developer position of 5E/hour.',
    //         jobLocation: 'Chisinau',
    //         dateCreated: '10/10/2019',
    //         activityId: 'IT'
    //     },
    // ];
}
