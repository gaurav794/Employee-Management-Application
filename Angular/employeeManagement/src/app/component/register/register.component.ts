import { Component, OnInit } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { AbstractControl, FormBuilder, FormGroup, Validators, ValidatorFn, ValidationErrors, FormControl } from '@angular/forms';
import { FormValidatorService } from '../util/form-validator.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder, private util: FormValidatorService) { }

  page_heading: string = "";
  registerForm: FormGroup = this.fb.group({});

  ngOnInit(): void {
    this.page_heading = "Register"
    this.registerForm = this.fb.group
      (
        {
          user_name: [null, Validators.required],
          email_id: [null, Validators.compose([Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])],
          phone_number: [null, Validators.compose([Validators.required,this.util.phoneNumberValidator()])],
          password: [null, Validators.compose([Validators.required,this.util.passwordLengthValidator()])],
          confirmPassword: [null, Validators.required]
        },
        {
          validator: this.util.matchPassword()
        }
      );
  }

  get registerFormControl() {
    return this.registerForm.controls;
  }


  public onSubmit() {
    //TODO
   if(this.registerForm.valid)
    console.log("VALID: ADD TO DATABASE");
   else
    this.util.validateForm(this.registerForm);

    console.log(this.registerForm);
  }

}

