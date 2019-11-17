import { Component, HostBinding, TemplateRef } from '@angular/core';
import { NotificationService } from '@services/notification.service';

@Component({
    selector: 'app-toasts',
    template: `
        <ngb-toast
                *ngFor="let toast of toastService.notifications"
                [class]="toast.classname"
                [autohide]="true"
                [delay]="toast.delay || 5000"
                aria-live="alert"
                role="alert"
                aria-atomic="true"
                (hide)="toastService.remove(toast)"
        >
            <ng-template [ngIf]="isTemplate(toast)" [ngIfElse]="text">
                <ng-template [ngTemplateOutlet]="toast.textOrTpl"></ng-template>
            </ng-template>

            <ng-template #text>{{ toast.textOrTpl }}</ng-template>
        </ngb-toast>
    `
})
export class ToastComponent {
    @HostBinding('class.nbg-toasts') host = true;

    constructor(public toastService: NotificationService) {
    }

    isTemplate(toast) {
        return toast.textOrTpl instanceof TemplateRef;
    }
}
