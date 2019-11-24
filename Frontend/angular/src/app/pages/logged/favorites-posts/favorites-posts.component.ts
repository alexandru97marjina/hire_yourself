import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { of, ReplaySubject, Subject } from 'rxjs';
import { catchError, map, switchMap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';
import { AuthHelper } from '@helpers/auth.helper';
import { PostInterface } from '@interfaces/post.interface';
import { PostService } from '@services/post.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { NotificationService } from '@services/notification.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-posts-list',
    templateUrl: './favorites-posts.component.html',
    styleUrls: ['./favorites-posts.component.scss']
})
export class FavoritesPostsComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public postsObserver: Subject<any> = new ReplaySubject(1);
    public posts$ = this.postsObserver.pipe(
        switchMap(() => this.postService.getFavorites(AuthHelper.getMe().id)),
        map( (response: ResponseInterface) => response.data),
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

    removeFromFavorites(event: boolean, post: PostInterface) {
        if (!event) {
            this.postService.deleteFavorite(post.id, this.me.id).pipe(
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

    modalClose(modal: NgbActiveModal) {
        modal.dismiss('Cross click');
        this.clearEdit();
    }
}
