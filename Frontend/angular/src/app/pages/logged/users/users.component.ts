import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { UserService } from '@services/user.service';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { debounceTime, map, switchMap } from 'rxjs/operators';
import { ResponseInterface } from '@interfaces/response.interface';
import { UserInterface } from '@interfaces/user.interface';
import { AuthHelper } from '@helpers/auth.helper';
import { FormBuilder, FormGroup } from '@angular/forms';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-users',
    templateUrl: './users.component.html',
    styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

    @ViewChild('content', {static: false}) content: TemplateRef<any>;
    public userObserver: Subject<any> = new ReplaySubject(1);
    public users$: Observable<UserInterface[]> = this.userObserver.pipe(
        switchMap((term: string) => {
            return this.userService.getList().pipe(
                map((response: ResponseInterface) => {
                    let data = response.data;
                    if (data && Array.isArray(data)) {
                        data = data.filter((item) => {
                            item.username =  item.firstName + ' ' + item.lastName;
                            return item.id !== AuthHelper.getMe().id && (
                                item.username.toLowerCase().includes(term.toLowerCase()) ||
                                item.email.toLowerCase().includes(term.toLowerCase())
                            );
                        });
                    }

                    return data;
                })
            );
        })
    );
    public userToShow: UserInterface = null;
    public searchForm: FormGroup;
    private lastFilters = null;

    constructor(
        private userService: UserService,
        private fb: FormBuilder,
        private modalService: NgbModal,
    ) {
    }

    ngOnInit(): void {
        this.userObserver.next('');
        this.initSearchForm();
    }

    initSearchForm() {
        this.searchForm = this.fb.group({
            name: this.fb.control('', [])
        });

        this.searchForm.valueChanges.pipe(
            debounceTime(200)
        ).subscribe((value) => {
            this.lastFilters = value.name;
            this.userObserver.next(this.lastFilters);
        });
    }

    openVerticallyCentered(content) {
        this.modalService.open(content, { centered: true, backdrop: 'static' });
    }

    modalClose(modal: NgbActiveModal) {
        modal.dismiss('Cross click');
        this.clearShow();
    }

    openShowUser(user: UserInterface) {
        this.openVerticallyCentered(this.content);
        this.userToShow = user;
    }

    clearShow() {
        this.userToShow = null;
    }
}
