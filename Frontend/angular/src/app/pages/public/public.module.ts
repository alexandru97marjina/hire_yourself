import { NgModule } from '@angular/core';
import { LoginComponent } from '@app/pages/public/login/login.component';
import { SharedModule } from '@app/shared.module';
import { PublicRoutingModule } from '@app/pages/public/public-routing.module';
import { RegisterComponent } from '@app/pages/public/register/register.component';


@NgModule({
    declarations: [
        LoginComponent,
        RegisterComponent,
    ],
    imports: [
        SharedModule,
        PublicRoutingModule,
    ],
})
export class PublicModule {
}
