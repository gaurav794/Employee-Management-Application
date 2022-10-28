import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './component/login/login.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { LogoutComponent } from './component/logout/logout.component';
import { PageHeadingComponent } from './component/page-heading/page-heading.component';
import {ReactiveFormsModule} from "@angular/forms";
import { RegisterComponent } from './component/register/register.component';
import { EmployeeComponent } from './component/employee/employee.component';
import { EmployeeSummaryComponent } from './component/employee/employee-summary/employee-summary.component';

@NgModule({
  declarations: [
    LoginComponent,
    DashboardComponent,
    LogoutComponent,
    PageHeadingComponent,
    RegisterComponent,
    EmployeeComponent,
    EmployeeSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [EmployeeSummaryComponent]
})
export class AppModule { }
