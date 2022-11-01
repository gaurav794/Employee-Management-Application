import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FormValidatorService } from 'src/app/component/util/form-validator-service/form-validator.service';

@Component({
  selector: 'app-payroll',
  templateUrl: './payroll.component.html',
  styleUrls: ['./payroll.component.css']
})
export class PayrollComponent implements OnInit {

  page_heading: string = "";
  months: number[] = [];
  modeOfPayment: string[] = [];
  payrollForm: FormGroup = this.fb.group({});

  constructor(private fb: FormBuilder, private util: FormValidatorService) { }

  ngOnInit(): void {
    this.page_heading = "Generate Payroll";
    //add 1 to 12 number in the months array
    for (let i = 0; i < 12; i++)
      this.months[i] = i + 1;
    this.modeOfPayment = ["NEFT", "Cash", "Cheque"];
    this.payrollForm = this.fb.group(
      {
        payroll_month: ["", Validators.required],
        attendance: [null, Validators.required],
        daily_wage: [{ value: null, disabled: true }],
        generated_salary: [{ value: null, disabled: true }],
        deductions: [null],
        net_pay: [{ value: null, disabled: true }],
        payment_mode: ["", Validators.required],
        date_added: [null, Validators.required],
        employee: ["", Validators.required]
      }
    );

    this.generateSalary();
    this.generateNetPay();
  }

  generateSalary() {

    this.payrollFormControl['attendance'].valueChanges.subscribe(
      () => {
        //Logic to calculate generated salary
        // this.payrollFormControl['generated_salary'].setValue(5000);
        // this.payrollFormControl['net_pay'].setValue(5000);
      });
    this.payrollFormControl['deductions'].valueChanges.subscribe(
      () => {
        //Logic to calculate net salary
        // this.payrollFormControl['net_pay'].setValue(3000);
      });
  }

  generateNetPay() {

  }

  get payrollFormControl() {
    return this.payrollForm.controls;
  }

  onSubmit() {
    if (this.payrollForm.valid) {
      console.log("VALID: ADD TO DATABASE");
      console.log(this.payrollForm.value);
    }
    else
      this.util.validateForm(this.payrollForm);
  }
}
