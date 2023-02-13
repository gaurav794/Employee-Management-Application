import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './component/dashboard_module/dashboard/dashboard.component';
import { EmployeeSummaryComponent } from './component/employee_module/employee-summary/employee-summary.component';
import { EmployeeComponent } from './component/employee_module/employee/employee.component';
import { PayrollSummaryComponent } from './component/payroll_module/payroll-summary/payroll-summary/payroll-summary.component';
import { PayrollComponent } from './component/payroll_module/payroll/payroll/payroll.component';
import { LoginComponent } from './component/user_module/login/login.component';
import { RegisterComponent } from './component/user_module/register/register.component';


const routes: Routes = [
  { path: "", component: DashboardComponent},
  { path: "login", component: LoginComponent},
  { path: "signup", component: RegisterComponent},
  { path: "add-emp", component: EmployeeComponent},
  { path: "emp-summary", component: EmployeeSummaryComponent},
  { path: "g-pr", component: PayrollComponent},
  { path: "pr-summary", component: PayrollSummaryComponent},
  { path: "**", redirectTo: ""}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
