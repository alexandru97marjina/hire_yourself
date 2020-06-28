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
        const keys = ['id', 'userId', 'title', 'description', 'email', 'active', 'dateCreated', 'dateUpdated', 'dateExpired'];
        if (object && typeof object === 'object') {
            return Object.entries(object).filter(([key, value]) => this.inArray(key, keys));
        }

        return object;
    }


    inArray(item, array) {
        let flag = false;

        if (array && Array.isArray(array)) {
            array.forEach(object => {
                if (object === item) {
                    flag = true;
                }
            });
        }

        return flag;
    }
}
