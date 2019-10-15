import { Component, OnInit } from '@angular/core';
import {POSTS_LIST} from '../../../data/posts';
@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.html',
  styleUrls: ['./post-list.scss']
})
export class PostListComponent implements OnInit {
    public postsList = POSTS_LIST;
    constructor() {
  }

  ngOnInit() {
  }

}
