import { Component, OnInit } from '@angular/core';
import { Payroll } from 'src/app/interface/payroll';
import { EmployeeManagementService } from 'src/app/service/employee-management/employee-management.service';
@Component({
  selector: 'app-payroll-summary',
  templateUrl: './payroll-summary.component.html',
  styleUrls: ['./payroll-summary.component.css']
})
export class PayrollSummaryComponent implements OnInit {
  page_heading: string = "";
  payrolls:Payroll[] = [];
  constructor(private employeeManagementService:EmployeeManagementService) { }

  ngOnInit(): void 
  {
    this.page_heading = "Payroll Summary";
    this.getPayrolls();
  }

  getPayrolls()
  {
   this.employeeManagementService.getPayrolls()
   .subscribe(
    res =>
    {
      this.payrolls = res;    
    }
    );
  }

}
