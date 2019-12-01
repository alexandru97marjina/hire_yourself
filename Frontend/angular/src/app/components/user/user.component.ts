import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserInterface } from '@interfaces/user.interface';

@Component({
    selector: 'app-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent {
    @Input() user: UserInterface;
    @Input() me: number;
    @Input() isFavorite = false;

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
