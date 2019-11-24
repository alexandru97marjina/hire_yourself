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
    @Input() isFavorite = false;

    @Output() edit: EventEmitter<void> = new EventEmitter<void>();
    @Output() remove: EventEmitter<void> = new EventEmitter<void>();
    @Output() favorite: EventEmitter<boolean> = new EventEmitter<boolean>();

    editPost() {
        this.edit.emit();
    }

    removePost() {
        this.remove.emit();
    }

    addToFavorites() {
        this.favorite.emit(true);
    }

    removeFromFavorites() {
        this.favorite.emit(false);
    }
}
