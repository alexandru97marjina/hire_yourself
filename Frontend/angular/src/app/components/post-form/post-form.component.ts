import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PostInterface } from '@interfaces/post.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
    selector: 'app-post-form',
    templateUrl: './post-form.component.html',
    styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {
    @Input() post: PostInterface;
    @Output() crossClick: EventEmitter<any> = new EventEmitter<any>();
    @Output() formSubmit: EventEmitter<any> = new EventEmitter<any>();

    form: FormGroup;
    public submitted = false;

    constructor(
        private modalService: NgbModal,
        private fb: FormBuilder,
    ) {
    }

    ngOnInit(): void {
        this.initForm();
    }

    initForm() {
        this.form = this.fb.group({
            title: this.fb.control(null, [Validators.required]),
            jobLocation: this.fb.control(null, [Validators.required]),
            salaryMin: this.fb.control(null, [Validators.required]),
            salaryMax: this.fb.control(null, []),
            minExperience: this.fb.control(null, [Validators.required]),
            email: this.fb.control(null, [Validators.required]),
            description: this.fb.control(null, []),
            education: this.fb.control(null, []),
            domain: this.fb.control(null, []),
        });
    }

    isSubmittedAndValid(control: string) {
        return this.submitted && this.form.get(control) && this.form.get(control).valid;
    }

    onCrossClick() {
        this.crossClick.emit(true);
    }

    onFormSubmit() {
        this.submitted = true;
        if (this.form.valid) {
            this.formSubmit.emit(true);
        }
    }
}
