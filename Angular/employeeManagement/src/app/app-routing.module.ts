import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard_module/dashboard/dashboard.component';
import { LoginComponent } from './component/user_module/login/login.component';


const routes: Routes = [{ path: "", component: DashboardComponent },
{
  path: "login", component: LoginComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
