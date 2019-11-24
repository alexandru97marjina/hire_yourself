import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PostInterface } from '@interfaces/post.interface';
import { catchError, map, switchMap } from 'rxjs/operators';
import { of, ReplaySubject, Subject } from 'rxjs';
import { ResponseInterface } from '@interfaces/response.interface';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthHelper } from '@helpers/auth.helper';
import { PostService } from '@services/post.service';
import { NotificationService } from '@services/notification.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-my-posts',
    templateUrl: './my-posts.component.html',
    styleUrls: ['./my-posts.component.scss']
})
export class MyPostsComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public posts$ = this.postsObserver.pipe(
        switchMap(() => this.postService.getList()),
        map( (response: ResponseInterface) => response.data.filter((item: PostInterface) => item.userId === AuthHelper.getMe().id)),
    );
    public me = AuthHelper.getMe();
    public postToEdit: PostInterface = null;

    public searchForm: FormGroup;

    constructor(
        private postService: PostService,
        private modalService: NgbModal,
        private fb: FormBuilder,
        private notificationService: NotificationService,
        private translate: TranslateService,
    ) {
    }

    ngOnInit(): void {
        this.postsObserver.next('');
        this.searchForm = this.fb.group({
            search: this.fb.control(null, []),
        });
    }

    openVerticallyCentered(content) {
        this.modalService.open(content, { centered: true });
    }

    refreshList() {
        this.postsObserver.next('');
        this.clearEdit();
    }

    clearEdit() {
        this.postToEdit = null;
    }

    removePost(post: PostInterface) {
        this.postService.deletePost(post).pipe(
            catchError((response) => {
                this.notificationService.error(response.error);
                return of(null);
            })
        ).subscribe((response: ResponseInterface) => {
            if (response) {
                this.notificationService.success(this.translate.instant('successful delete'));
            }

            this.refreshList();
        });
    }

    modalSubmit(modal: NgbActiveModal) {
        modal.close();
        this.refreshList();
        this.clearEdit();
    }

    modalClose(modal: NgbActiveModal) {
        modal.dismiss('Cross click');
        this.clearEdit();
    }

    editPost(post: PostInterface) {
        this.postToEdit = post;
        this.openVerticallyCentered(this.content);
    }
}
