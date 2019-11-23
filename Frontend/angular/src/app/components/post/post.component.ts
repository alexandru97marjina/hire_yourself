import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PostInterface } from '@interfaces/post.interface';

@Component({
    selector: 'app-post',
    templateUrl: './post.component.html',
    styleUrls: ['./post.component.scss']
})
export class PostComponent {
    @Input() post: PostInterface;
    @Input() me: number;

    @Output() edit: EventEmitter<void> = new EventEmitter<void>();
    @Output() remove: EventEmitter<void> = new EventEmitter<void>();

    editPost() {
        this.edit.emit();
    }

    removePost() {
        this.remove.emit();
    }
}
