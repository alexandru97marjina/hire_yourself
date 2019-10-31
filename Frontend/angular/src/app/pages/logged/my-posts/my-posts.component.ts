import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-my-posts',
    templateUrl: './my-posts.component.html',
    styleUrls: ['./my-posts.component.scss']
})
export class MyPostsComponent implements OnInit {

    public searchForm: FormGroup;

    constructor(
        private fb: FormBuilder,
    ) {
    }

    ngOnInit(): void {
        this.searchForm = this.fb.group({
            search: this.fb.control(null, []),
        });
    }
}
