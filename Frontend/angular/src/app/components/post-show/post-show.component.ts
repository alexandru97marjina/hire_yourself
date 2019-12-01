import { Component, EventEmitter, Input, Output } from '@angular/core';
import { PostInterface } from '@interfaces/post.interface';

@Component({
    selector: 'app-post-show',
    templateUrl: './post-show.component.html',
    styleUrls: ['./post-show.component.scss']
})
export class PostShowComponent {
    @Input() post: PostInterface;
    @Input() me: number;

    @Output() closeShow: EventEmitter<void> = new EventEmitter<void>();

    close() {
        this.closeShow.emit();
    }

    entries(object = null) {
        if (object && typeof object === 'object') {
            return Object.entries(object);
        }

        return object;
    }
}
