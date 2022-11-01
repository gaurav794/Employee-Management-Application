import { Component, OnInit } from '@angular/core';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { FormValidatorService } from '../../util/form-validator-service/form-validator.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  
  page_heading: string = "";
  employeeForm: FormGroup = this.fb.group({});

  constructor(private fb: FormBuilder, private util: FormValidatorService) { }

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

  get EmployeeFormControl()
  {
    return this.employeeForm.controls;
  }

  onSubmit() 
  {
    if(this.employeeForm.valid){
      console.log("VALID: ADD TO DATABASE");
      console.log(this.employeeForm.value);
    }
    else
      this.util.validateForm(this.employeeForm);
  }


}
