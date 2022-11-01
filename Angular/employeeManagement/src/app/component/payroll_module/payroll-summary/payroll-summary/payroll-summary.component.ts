import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payroll-summary',
  templateUrl: './payroll-summary.component.html',
  styleUrls: ['./payroll-summary.component.css']
})
export class PayrollSummaryComponent implements OnInit {
  page_heading: string = "";

  constructor() { }

  ngOnInit(): void 
  {
    this.page_heading = "Payroll Summary";
  }

}
