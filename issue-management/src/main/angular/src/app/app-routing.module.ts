import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { AppComponent } from './app.component';
import { LoginComponent } from './component/login/login.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {path:"dashboard",component:DashboardComponent,canActivate:[AuthGuard]},
  {path:'',redirectTo:"dashboard",pathMatch:'full'},
  {path:'login',component:LoginComponent}
];
@NgModule({                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
  imports: [RouterModule.forRoot(routes, {useHash: true} )],
  exports: [RouterModule]
})
export class AppRoutingModule { }
