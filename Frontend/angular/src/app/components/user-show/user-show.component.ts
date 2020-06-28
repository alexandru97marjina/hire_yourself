import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserInterface } from '@interfaces/user.interface';

@Component({
    selector: 'app-user-show',
    templateUrl: './user-show.component.html',
    styleUrls: ['./user-show.component.scss']
})
export class UserShowComponent {
    @Input() user: UserInterface;
    @Input() me: number;

    @Output() closeShow: EventEmitter<void> = new EventEmitter<void>();

    close() {
        this.closeShow.emit();
    }

    entries(object = null) {
        const keys = ['id', 'email', 'firstName', 'lastName', 'age', 'address', 'phone', 'active'];
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
