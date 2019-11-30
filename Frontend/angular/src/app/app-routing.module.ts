import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { IsAuthenticatedGuard } from '@app/guards/isAuthenticated.guard';


const routes: Routes = [
    {
        path: 'public',
        loadChildren: () => import('./pages/public/public.module').then(mod => mod.PublicModule),
    },
    {
        path: '',
        loadChildren: () => import('./pages/logged/logged.module').then(mod => mod.LoggedModule),
        canLoad: [IsAuthenticatedGuard]
    },
    {
        path: '**',
        redirectTo: '/',
        pathMatch: 'full'
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
