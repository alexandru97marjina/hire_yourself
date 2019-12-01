import { Component, OnInit } from '@angular/core';
import { UserInterface } from '@interfaces/user.interface';
import { EducationService } from '@services/education.service';
import { ActivityService } from '@services/activity.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '@services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseInterface } from '@interfaces/response.interface';
import { NotificationService } from '@services/notification.service';
import { AuthHelper } from '@helpers/auth.helper';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { ActivityInterface } from '@interfaces/activity.interface';
import { map, switchMap } from 'rxjs/operators';
import { EducationInterface } from '@interfaces/education.interface';

@Component({
    selector: 'app-user-edit',
    templateUrl: './user-edit.component.html',
    styleUrls: ['./user-edit.component.scss']
})
export class UserEditComponent implements OnInit {
    public form: FormGroup;
    public user: UserInterface;
    public me = AuthHelper.getMe();
    public experienceObserver: Subject<string> = new ReplaySubject(1);
    public experiences$: Observable<ActivityInterface[]> = this.experienceObserver.pipe(
        switchMap(() => this.activityService.getList()),
        map((response: ResponseInterface) => response.data as ActivityInterface[])
    );
    public educationObserver: Subject<string> = new ReplaySubject();
    public educations$: Observable<EducationInterface[]> = this.educationObserver.pipe(
        switchMap(() => this.educationService.getList()),
        map((response: ResponseInterface) => response.data.map(
            (item: EducationInterface) => ({...item, mixedName: (item.specialityName + ' (' + item.studyGrade + ') ')})
        ) as EducationInterface[])
    );

    public experiences = [];
    public submitted = false;

    constructor(
        private userService: UserService,
        private educationService: EducationService,
        private activityService: ActivityService,
        private fb: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit(): void {
        this.initEditUser();
        this.experienceObserver.next('');
        this.educationObserver.next('');
    }

    initForm(user: UserInterface) {
        this.form = this.fb.group({
            firstName: this.fb.control(user ? user.firstName : '', [Validators.required]),
            lastName: this.fb.control(user ? user.lastName : '', [Validators.required]),
            email: this.fb.control(user ? user.email : '', [Validators.required, Validators.email]),
            address: this.fb.control(user ? user.address : '', [Validators.required]),
            phone: this.fb.control(user ? user.phone : '', [Validators.required]),
            password: this.fb.control('', [Validators.minLength(6)]),
            passwordConfirm: this.fb.control('',
                this.confirmPasswordControl() ? [Validators.required] : []
            ),
            age: this.fb.control(user ? user.age : '', []),
            educationId: this.fb.control(user && user.education ? user.education.id : null, [Validators.required]),
            experience: this.fb.control(
                {value: user && user.experience ? user.experience.map(item => item.id) : null, disabled: true},
                [Validators.required]),
            graduationYear: this.fb.control(user ? user.graduationYear : '', []),
        }, {validators: this.checkPasswords});
    }

    confirmPasswordControl() {
        return this.form && this.form.get('password').value.length;
    }

    initEditUser() {
        const id = this.route.snapshot.params.id || null;
        if (!id || isNaN(Number(id))) {
            this.notificationService.error('id in url is wrong or missing');
            return;
        }

        this.userService.getUser(id).subscribe((response: ResponseInterface) => {
            const user = response.data as UserInterface;
            if (user.education) {
                user.education.mixedName = ( user.education.specialityName + ' (' +  user.education.studyGrade + ') ');
            }

            this.experiences = user.experience;
            this.user = user;
            this.initForm(this.user);
        });
    }


    isSubmittedAndValid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).valid && this.form.get(control).value;
    }

    isSubmittedAndInvalid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).invalid;
    }


    clearExperience(array) {
        const entries = [];

        array.forEach(item => {
            let newObj = {};
            Object.entries(item).forEach(entry => {
                if (entry[0] !== 'id' && entry[0] !== 'monthsOfExperience') {
                    newObj = {...newObj, [entry[0]]: entry[1]};
                }
            });
            entries.push(newObj);
        });

        return entries;
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
        values.experience = this.clearExperience(this.user.experience);
        this.userService.updateUser(this.me.id, values).subscribe(() => {
            if (this.user.id === this.me.id) {
                this.notificationService.success('profile successful updated');
            } else {
                this.notificationService.success('user successful updated');
            }

            this.router.navigate(['/', 'users']);
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
}
