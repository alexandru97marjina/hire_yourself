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
import { UserService } from '@services/user.service';
import { ActivityService } from '@services/activity.service';
import { EducationService } from '@services/education.service';
import { SearchHelper } from '@helpers/search.helper';

@Component({
    selector: 'app-posts-list',
    templateUrl: './posts-list.component.html',
    styleUrls: ['./posts-list.component.scss']
})
export class PostsListComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public usersObserver: Subject<string> = new ReplaySubject(1);
    public activityObserver: Subject<any> = new ReplaySubject(1);
    public educationObserver: Subject<any> = new ReplaySubject(1);
    public lastFilters = null;
    public posts$ = this.postsObserver.pipe(
        switchMap((term: any) => {
            return this.postService.getList().pipe(
                map( (response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        this.collectLocations(data);
                        data = SearchHelper.applyFilters(data, term);
                    }

                    return data;
                }),
            );
        }),
    );
    public users$ = this.usersObserver.pipe(
        switchMap((term: string) => {
            return this.userService.getList().pipe(
                map((response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        data = data.map(item => {
                            item.username = this.emptyString(item.firstName) + ' ' + this.emptyString(item.lastName);
                            if (!item.username.trim().length) {
                                item.username = item.email;
                            }

                            return item;
                        });
                        if (term || term && term.length) {
                            data = data.filter((item) => item.username.toLowerCase().includes(term.toLowerCase()));
                        }
                    }

                    return data;
                })
            );
        })
    );
    public activities$ = this.activityObserver.pipe(
        switchMap((term: string) => {
            return this.activityService.getList().pipe(
                map((response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        if (term || term && term.length) {
                            data = data.filter((item) => item.activityName.toLowerCase().includes(term.toLowerCase()));
                        }
                    }

                    return data;
                })
            );
        })
    );
    public educations$ = this.educationObserver.pipe(
        switchMap((term: string) => {
            return this.educationService.getList().pipe(
                map((response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        if (term || term && term.length) {
                            data = data.filter((item) => item.specialityName.toLowerCase().includes(term.toLowerCase()));
                        }
                    }

                    return data;
                })
            );
        })
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
        private userService: UserService,
        private activityService: ActivityService,
        private educationService: EducationService,
    ) {
    }

    ngOnInit(): void {
        this.initObservers();
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

    private initObservers() {
        this.postsObserver.next(this.lastFilters);
        this.usersObserver.next('');
        this.activityObserver.next('');
        this.educationObserver.next('');
    }

    private emptyString(item: any): string {
        if (!item || typeof item !== 'string' && typeof item !== 'number') {
            return '';
        }

        return String(item);
    }

    initSearchForm() {
        this.searchForm = this.fb.group({
            name: this.fb.control('', []),
            startDate: this.fb.control('', []),
            endDate: this.fb.control('', []),
            user: this.fb.control(null, []),
            location: this.fb.control(null, []),
            activity: this.fb.control(null, []),
            education: this.fb.control(null, []),
        });

        this.subH.subscribe = this.searchForm.valueChanges.pipe(
            debounceTime(200)
        ).subscribe((value) => {
            this.lastFilters = value;
            this.postsObserver.next(this.lastFilters);
        });
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
        this.modalService.open(content, { centered: true, backdrop: 'static' });
    }

    refreshList() {
        this.postsObserver.next(this.lastFilters);
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
