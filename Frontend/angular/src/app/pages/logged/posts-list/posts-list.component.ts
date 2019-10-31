import { Component, OnInit } from '@angular/core';
import { PostService } from '@services/post.service';
import { ReplaySubject, Subject } from 'rxjs';
import { map, switchMap, tap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';

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

    searchForm: FormGroup;

    constructor(
        private postService: PostService,
        private modalService: NgbModal,
        private fb: FormBuilder,
    ) {
    }

    ngOnInit() {
        this.postsObserver.next('');
        this.searchForm = this.fb.group({
            search: this.fb.control(null, []),
        });
    }

    openVerticallyCentered(content) {
        this.modalService.open(content, { centered: true });
    }
}
