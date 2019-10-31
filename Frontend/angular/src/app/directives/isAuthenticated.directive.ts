import { Directive, OnInit, TemplateRef, ViewContainerRef } from '@angular/core';
import { AuthHelper } from '@helpers/auth.helper';

@Directive({
    selector: '[isAuthenticated]'
})
export class IsAuthenticatedDirective implements OnInit {
    constructor(
        private templateRef: TemplateRef<any>,
        private viewContainer: ViewContainerRef,
    ) {
    }

    ngOnInit(): void {
        if (AuthHelper.getAuthenticated()) {
            this.viewContainer.createEmbeddedView(this.templateRef);
        } else {
            this.viewContainer.clear();
        }
    }
}
