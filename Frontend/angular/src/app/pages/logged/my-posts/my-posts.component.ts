import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { PostInterface } from '@interfaces/post.interface';
import { catchError, debounceTime, map, switchMap } from 'rxjs/operators';
import { of, ReplaySubject, Subject } from 'rxjs';
import { ResponseInterface } from '@interfaces/response.interface';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthHelper } from '@helpers/auth.helper';
import { PostService } from '@services/post.service';
import { NotificationService } from '@services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { SubHolderHelper } from '@helpers/subHolder.helper';

@Component({
    selector: 'app-my-posts',
    templateUrl: './my-posts.component.html',
    styleUrls: ['./my-posts.component.scss']
})
export class MyPostsComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public posts$ = this.postsObserver.pipe(
        switchMap((term: string) => {
            return this.postService.getList().pipe(
                map( (response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        data = data.filter((item: PostInterface) => {
                            return item.title.toLowerCase().includes(term.toLowerCase()) && item.userId === AuthHelper.getMe().id;
                        });
                    }

                    return data;
                }),
            );
        }),
    );
    public me = AuthHelper.getMe();
    public postToEdit: PostInterface = null;

    public searchForm: FormGroup;
    private subH: SubHolderHelper = new SubHolderHelper();

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
        this.initSearchForm();
    }

    initSearchForm() {
        this.searchForm = this.fb.group({
            name: this.fb.control(null, []),
        });

        this.subH.subscribe = this.searchForm.get('name').valueChanges.pipe(
            debounceTime(200)
        ).subscribe((value) => this.postsObserver.next(value));
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
