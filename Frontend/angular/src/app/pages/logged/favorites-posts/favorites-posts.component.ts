import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
    selector: 'app-posts-list',
    templateUrl: './favorites-posts.component.html',
    styleUrls: ['./favorites-posts.component.scss']
})
export class FavoritesPostsComponent implements OnInit {

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
