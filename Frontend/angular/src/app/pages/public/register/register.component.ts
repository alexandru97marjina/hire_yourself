import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { catchError, map, switchMap } from 'rxjs/operators';
import { NotificationService } from '@services/notification.service';
import { Observable, of, ReplaySubject, Subject } from 'rxjs';
import { ResponseInterface } from '@interfaces/response.interface';
import { EducationInterface } from '@interfaces/education.interface';
import { UserService } from '@services/user.service';
import { EducationService } from '@services/education.service';
import { ActivityService } from '@services/activity.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
    public form: FormGroup;
    public educationObserver: Subject<string> = new ReplaySubject();
    public educations$: Observable<EducationInterface[]> = this.educationObserver.pipe(
        switchMap(() => this.educationService.getList(false)),
        map((response: ResponseInterface) => response.data.map(
            (item: EducationInterface) => ({...item, mixedName: (item.specialityName + ' (' + item.studyGrade + ') ')})
        ) as EducationInterface[])
    );

    public experiences = [];
    public submitted = false;

    private readonly PHONE_PATTERN = /^(?:(?:\+|00)373|0)\s*[1-9](\d){7}$/;

    constructor(
        private userService: UserService,
        private educationService: EducationService,
        private activityService: ActivityService,
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
        private modalService: NgbModal,
    ) {
    }

    ngOnInit(): void {
        this.initForm();
        this.educationObserver.next('');
    }

    initForm() {
        this.form = this.fb.group({
            firstName: this.fb.control('', [Validators.required]),
            lastName: this.fb.control('', [Validators.required]),
            email: this.fb.control('', [Validators.required, Validators.email]),
            address: this.fb.control('', [Validators.required]),
            phone: this.fb.control('', [Validators.required, Validators.pattern(this.PHONE_PATTERN)]),
            password: this.fb.control('', [Validators.minLength(6), Validators.required]),
            passwordConfirm: this.fb.control('',
                this.confirmPasswordControl() ? [Validators.required] : []
            ),
            age: this.fb.control('', []),
            educationId: this.fb.control(1, []),
            experience: this.fb.control(
                [
                    {
                        companyName: 'test',
                        dateEnded: 1593475200000,
                        dateStarted: 1590969600000,
                    }
                ],
                []
            ),
            graduationYear: this.fb.control('2020', []),
        }, {validators: this.checkPasswords});
    }

    confirmPasswordControl() {
        return this.form && this.form.get('password').value.length;
    }

    isSubmittedAndValid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).valid && this.form.get(control).value;
    }

    isSubmittedAndInvalid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).invalid;
    }

    submit() {
        this.submitted = true;

        if (!this.form.valid) {
            this.notificationService.error('form is not correctly completed');
            return;
        }

        const values = this.form.value;
        delete values.passwordConfirm;
        if (!values.password.trim().length) {
            delete values.password;
        }

        values.activityId = 1;
        this.userService.createUser(values, false)
            .pipe(
                catchError((error) => {
                    this.notificationService.error(error.message);
                    return of(null);
                })
            ).subscribe((data) => {
                if (data) {
                    this.notificationService.success('successful register');
                    this.router.navigate(['/', 'public', 'login']);
                }
            });
    }

    checkPasswords(group: FormGroup) { // here we have the 'passwords' group
        const pass = group.get('password').value;
        const confirmPass = group.get('passwordConfirm').value;

        const result = pass === confirmPass ? null : { notSame: true };
        if (result !== null) {
            group.get('passwordConfirm').setErrors(result);
        }

        return result;
    }

    openVerticallyCentered(content) {
        this.modalService.open(content, { centered: true, backdrop: 'static' });
    }

    experienceModalSubmit(modal: NgbActiveModal, event = null) {
        if (modal) {
            modal.close();
        }

        const experience: any[] = this.form.get('experience').value || [];
        if (event) {
            experience.push(event);
        }

        experience.forEach(item => {
            if (!this.experiences.includes(item)) {
                this.experiences.push(item);
            }
        });
        this.form.get('experience').setValue(experience);
    }

    educationModalSubmit(modal: NgbActiveModal, event = null) {
        if (modal) {
            modal.close();
        }

        this.educationService.createEducation(event, false).subscribe( (data) => {
            this.educationObserver.next('');
        });
    }

    modalClose(modal: NgbActiveModal) {
        modal.dismiss('Cross click');
    }
}
