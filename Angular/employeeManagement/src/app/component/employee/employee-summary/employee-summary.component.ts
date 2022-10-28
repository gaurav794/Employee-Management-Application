import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-employee-summary',
  templateUrl: './employee-summary.component.html',
  styleUrls: ['./employee-summary.component.css']
})
export class EmployeeSummaryComponent implements OnInit {
  page_heading: string = "";

  constructor() { }

  ngOnInit(): void 
  {
    this.page_heading = "Employee Summary";
  }

}
