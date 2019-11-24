import { NgModule } from '@angular/core';

import { LoginComponent } from '@app/pages/public/login/login.component';
import { SharedModule } from '@app/shared.module';
import { PublicRoutingModule } from '@app/pages/public/public-routing.module';


@NgModule({
    declarations: [
        LoginComponent,
    ],
    imports: [
        SharedModule,
        PublicRoutingModule,
    ],
})
export class PublicModule {
}
