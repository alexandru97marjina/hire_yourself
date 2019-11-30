import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { PostService } from '@services/post.service';
import { Observable, of, ReplaySubject, Subject } from 'rxjs';
import { catchError, debounceTime, map, switchMap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthHelper } from '@helpers/auth.helper';
import { PostInterface } from '@interfaces/post.interface';
import { NotificationService } from '@services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { SubHolderHelper } from '@helpers/subHolder.helper';

@Component({
    selector: 'app-posts-list',
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public posts$ = this.postsObserver.pipe(
        switchMap((term: string) => {
            return this.postService.getList().pipe(
                map( (response: ResponseInterface) => {
                    let data = response.data;
                    this.collectLocations(data);
                    if (data && Array.isArray(data)) {
                        data = data.filter(item => item.title.toLowerCase().includes(term.toLowerCase()));
                    }

                    return data;
                }),
            );
        }),
    );
    public me = AuthHelper.getMe();
    public postToEdit: PostInterface = null;
    public favorites$: Observable<any>;
    public locations: string[] = [];

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
        this.refreshFavoritesIds();
        this.favorites$ = this.postService.favoritesIds;
        this.initSearchForm();
    }

    isIncluded(values, post: PostInterface) {
        if (values && Array.isArray(values)) {
            return values.includes(post.id);
        }

        return false;
    }

    initSearchForm() {
        this.searchForm = this.fb.group({
            name: this.fb.control(null, []),
        });

        this.subH.subscribe = this.searchForm.get('name').valueChanges.pipe(
            debounceTime(200)
        ).subscribe((value) => this.postsObserver.next(value));
    }

    collectLocations(data: PostInterface[]) {
        this.locations = [];
        data.forEach((item: PostInterface) => {
            if (!this.locations.find((location) => item.jobLocation === location)) {
                this.locations.push(item.jobLocation);
            }
        });
    }

    openVerticallyCentered(content) {
        this.modalService.open(content, { centered: true });
    }

    refreshList() {
        this.postsObserver.next('');
        this.clearEdit();
        this.refreshFavoritesIds();
    }

    refreshFavoritesIds() {
        this.postService.refreshCount();
        this.postService.refreshList();
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

    handleFavorite(event: boolean, post: PostInterface) {
        if (event) {
            this.addToFavorites(post);
        } else {
            this.removeFromFavorites(post);
        }
    }

    removeFromFavorites(post: PostInterface) {
        this.postService.deleteFavorite(post.id, this.me.id).pipe(
            catchError((response) => {
                this.notificationService.error(response.error);
                return of(null);
            })
        ).subscribe((response: ResponseInterface) => {
            if (response) {
                this.notificationService.success(this.translate.instant('successfully removed from favorites'));
            }

            this.refreshList();
        });
    }

    addToFavorites(post: PostInterface) {
        this.postService.addToFavorites(post.id, this.me.id).pipe(
            catchError((response) => {
                this.notificationService.error(response.error);
                return of(null);
            })
        ).subscribe((response: ResponseInterface) => {
            if (response) {
                this.notificationService.success(this.translate.instant('successfully added to favorites'));
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
