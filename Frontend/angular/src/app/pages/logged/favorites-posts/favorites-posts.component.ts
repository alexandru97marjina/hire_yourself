import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { of, ReplaySubject, Subject } from 'rxjs';
import { catchError, debounceTime, map, switchMap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';
import { AuthHelper } from '@helpers/auth.helper';
import { PostInterface } from '@interfaces/post.interface';
import { PostService } from '@services/post.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotificationService } from '@services/notification.service';
import { TranslateService } from '@ngx-translate/core';
import { SubHolderHelper } from '@helpers/subHolder.helper';
import { SearchHelper } from '@helpers/search.helper';
import { UserService } from '@services/user.service';
import { ActivityService } from '@services/activity.service';
import { EducationService } from '@services/education.service';

@Component({
    selector: 'app-posts-list',
    templateUrl: './favorites-posts.component.html',
    styleUrls: ['./favorites-posts.component.scss']
})
export class FavoritesPostsComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    @ViewChild('contentShow', {static: false}) contentShow: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public usersObserver: Subject<string> = new ReplaySubject(1);
    public activityObserver: Subject<any> = new ReplaySubject(1);
    public educationObserver: Subject<any> = new ReplaySubject(1);
    public lastFilters = null;
    public locations: string[] = [];
    public posts$ = this.postsObserver.pipe(
        switchMap((term: any) => {
            return this.postService.getFavorites(AuthHelper.getMe().id).pipe(
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
    public postToShow: PostInterface = null;

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
        this.initSearchForm();
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
    }

    clearEdit() {
        this.postToEdit = null;
        this.postToShow = null;
    }

    removeFromFavorites(event: boolean, post: PostInterface) {
        if (!event) {
            this.postService.deleteFavorite(this.me.id, post.id).pipe(
                catchError((response) => {
                    this.notificationService.error(response.error);
                    return of(null);
                })
            ).subscribe((response: ResponseInterface) => {
                if (response) {
                    this.notificationService.success(this.translate.instant('successfully removed from favorites'));
                }

                this.postService.refreshCount();
                this.refreshList();
            });
        }
    }

    modalSubmit(modal: NgbActiveModal) {
        modal.close();
        this.refreshList();
        this.clearEdit();
    }

    openShow(post: PostInterface) {
        this.postToShow = post;
        this.openVerticallyCentered(this.contentShow);
    }

    modalClose(modal: NgbActiveModal) {
        modal.dismiss('Cross click');
        this.clearEdit();
    }
}
