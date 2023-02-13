import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { Employee } from 'src/app/interface/employee';
import { RestResponseStatus } from 'src/app/interface/rest-response-status';
import { EmployeeManagementService } from 'src/app/service/employee-management/employee-management.service';
import { ToastService } from 'src/app/service/toast/toast.service';
import { FormValidatorService } from '../../util/form-validator-service/form-validator.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  page_heading: string = "";
  employeeForm: FormGroup = this.fb.group({});

  constructor(private fb: FormBuilder, private util: FormValidatorService, private employeeManagementService: EmployeeManagementService,
    private toastService: ToastService) { }

  ngOnInit(): void {
    this.page_heading = "Add Employee";
    this.employeeForm = this.fb.group(
      {
        pid: [null, Validators.required],
        name: [null, Validators.required],
        address: [null, Validators.required],
        phone_number: [null, Validators.compose([Validators.required, this.util.phoneNumberValidator()])],
        designation: ['', Validators.required],
        daily_wage: [null, Validators.required],
        doj: [null, Validators.required]
      }
    );
  }

  get EmployeeFormControl() {
    return this.employeeForm.controls;
  }

  addEmployee() {
    if (this.employeeForm.valid) {
      let new_employee: Employee = this.employeeForm.value;
      this.employeeManagementService.addEmployee(new_employee).
        subscribe
        ({
          next: (res) => {
            // TODO: Toast Notifications for success
            this.toastService.show(res.status, res.message, 4000);
          },
          error: (err:HttpErrorResponse) => {
             // TODO: Toast Notifications for error
             let errData:RestResponseStatus = err.error;
            this.toastService.show(errData.status, errData.message, 4000);
           }
        });
        //reset form values
        this.util.resetForm(this.employeeForm);
    }
    else
      this.util.validateForm(this.employeeForm);
  }


}
