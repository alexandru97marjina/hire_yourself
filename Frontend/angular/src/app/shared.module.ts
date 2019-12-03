import { NgModule } from '@angular/core';

import { NgbDropdownModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';
import { NgSelectModule } from '@ng-select/ng-select';
import { Pipes } from '@app/pipes';
import { Directives } from '@app/directives';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { ToastComponent } from '@components/toast/toast.component';
import { ExperienceFormComponent } from '@components/experience-form/experience-form.component';

@NgModule({
    declarations: [
        ...Pipes,
        ...Directives,
        ToastComponent,
        ExperienceFormComponent,
    ],
    imports: [
        HttpClientModule,
        NgbModule,
        NgbDropdownModule,
        NgSelectModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
    ],
    providers: [
        HttpClient,
        IsAuthenticatedGuard,
    ],
    exports: [
        ...Pipes,
        ...Directives,
        ExperienceFormComponent,
        NgbModule,
        NgbDropdownModule,
        NgSelectModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        CommonModule,
        ToastComponent,
    ]
})
export class SharedModule {
}
