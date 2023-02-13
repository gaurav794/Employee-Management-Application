import { Component, NgModule, OnInit } from '@angular/core';
import { Employee } from 'src/app/interface/employee';
import { EmployeeManagementService } from 'src/app/service/employee-management/employee-management.service';
@Component({
  selector: 'app-employee-summary',
  templateUrl: './employee-summary.component.html',
  styleUrls: ['./employee-summary.component.css']
})
export class EmployeeSummaryComponent implements OnInit 
{
  page_heading: string = "";
  employees: Employee[] = [];

  constructor(private employeeManagementService: EmployeeManagementService) { }

  ngOnInit(): void 
  {
    this.page_heading = "Employee Summary";
    this.getEmployees();
  }

  getEmployees()
  {
   this.employeeManagementService.getEmployees()
   .subscribe(
    res =>
    {
      this.employees = res;
      console.log(res);     
    }
    );
  }


}
