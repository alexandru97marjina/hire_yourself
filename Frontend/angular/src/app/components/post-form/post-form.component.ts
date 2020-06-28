import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { PostInterface } from '@interfaces/post.interface';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivityService } from '@services/activity.service';
import { EducationService } from '@services/education.service';
import { FileService } from '@services/file.service';
import { AuthHelper } from '@helpers/auth.helper';
import { PostService } from '@services/post.service';

@Component({
    selector: 'app-post-form',
    templateUrl: './post-form.component.html',
    styleUrls: ['./post-form.component.scss']
})
export class PostFormComponent implements OnInit {
    @Input() post: PostInterface;
    @Output() crossClick: EventEmitter<any> = new EventEmitter<any>();
    @Output() formSubmit: EventEmitter<any> = new EventEmitter<any>();

    public form: FormGroup;
    public submitted = false;

    constructor(
        private modalService: NgbModal,
        private fb: FormBuilder,
        private activityService: ActivityService,
        private educationService: EducationService,
        private fileService: FileService,
        private postService: PostService,
    ) {
    }

    ngOnInit(): void {
        this.initForm();
    }

    initForm() {
        this.form = this.fb.group({
            title: this.fb.control(this.post ? this.post.title : null, [Validators.required]),
            jobLocation: this.fb.control(this.post ? this.post.jobLocation : null, [Validators.required]),
            salaryMin: this.fb.control(this.post ? this.post.salaryMin : null, [Validators.required]),
            salaryMax: this.fb.control(this.post ? this.post.salaryMax : null, []),
            email: this.fb.control(AuthHelper.getMe().email, [Validators.required]),
            description: this.fb.control(this.post ? this.post.description : null, []),
            educationId: this.fb.control(1, []),
            activityId: this.fb.control(1, []),
            imagePath: this.fb.control(this.post ? this.post.imagePath : null, []),
            file: this.fb.control(null, []),
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

    public fileChange({target}) {
        if (target.files && target.files[0]) {
            const file = target.files[0];
            this.form.get('file').setValue(file);
        }
    }

    upload() {
        const file = this.form.get('file').value;
        const image: string = this.form.get('imagePath').value;

        if (file && (!image || !image.includes(file.name))) {
            this.fileService.upload(file)
                // .pipe(switchMap((response: FileResponseInterface) => {
                //         return forkJoin([this.fileService.download(response.fileName)]).pipe(map(() => response));
                //     }),
                //     last())
                .subscribe((response: any) => {
                this.form.get('imagePath').setValue(response.filePath);
                this.form.get('file').setValue(null);
            });
        }
    }

    onFormSubmit() {
        this.submitted = true;
        const data = this.form.value;

        if (this.form.valid) {
            this.upload();
            const post: PostInterface = PostService.mapData(data);
            if (this.post) {
                this.postService.updatePost(this.post.id, post).subscribe(() => {
                    this.formSubmit.emit(true);
                });
            } else {
                this.postService.createPost(post).subscribe(() => {
                    this.formSubmit.emit(true);
                });
            }
        }
    }

}

