import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './component/dashboard_module/dashboard/dashboard.component';
import { EmployeeSummaryComponent } from './component/employee_module/employee-summary/employee-summary.component';
import { EmployeeComponent } from './component/employee_module/employee/employee.component';
import { LoginComponent } from './component/user_module/login/login.component';
import { LogoutComponent } from './component/user_module/logout/logout.component';
import { RegisterComponent } from './component/user_module/register/register.component';
import { PageHeadingComponent } from './component/util/page-heading/page-heading.component';
import { PayrollComponent } from './component/payroll_module/payroll/payroll/payroll.component';
import { PayrollSummaryComponent } from './component/payroll_module/payroll-summary/payroll-summary/payroll-summary.component';
import { FormValidatorService } from './component/util/form-validator-service/form-validator.service';

@NgModule({
  declarations: [
    LoginComponent,
    DashboardComponent,
    LogoutComponent,
    PageHeadingComponent,
    RegisterComponent,
    EmployeeComponent,
    EmployeeSummaryComponent,
    PayrollComponent,
    PayrollSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  // First Page to Load
  bootstrap: [PayrollComponent]
})
export class AppModule { }
