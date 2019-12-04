import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NotificationService } from '@services/notification.service';

@Component({
    selector: 'app-education-form',
    templateUrl: './education-form.component.html',
    styleUrls: ['./education-form.component.scss']
})
export class EducationFormComponent implements OnInit {
    @Output() crossClick: EventEmitter<any> = new EventEmitter<any>();
    @Output() formSubmit: EventEmitter<any> = new EventEmitter<any>();

    public form: FormGroup;
    public submitted = false;

    constructor(
        private modalService: NgbModal,
        private fb: FormBuilder,
        private notificationService: NotificationService,
    ) {
    }

    ngOnInit(): void {
        this.initForm();
    }

    initForm() {
        this.form = this.fb.group({
            specialityName: this.fb.control('', Validators.required),
            studyGrade: this.fb.control('', Validators.required),
        });
    }

    isSubmittedAndValid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).valid && this.form.get(control).value;
    }

    isSubmittedAndInvalid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).invalid;
    }

    onCrossClick() {
        this.crossClick.emit(true);
    }

    onFormSubmit() {
        this.submitted = true;
        const data = this.form.value;
        if (this.form.invalid) {
            this.notificationService.error('form is completed incorrectly');
            return;
        }

        this.formSubmit.emit(data);
    }

}

