import { formatNumber } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import { FormValidatorService } from '../util/form-validator.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{

  page_heading: string ="";
  loginForm:FormGroup = this.fb.group({});

  constructor(private fb: FormBuilder,private util:FormValidatorService) { }

  ngOnInit(): void 
  {
    this.page_heading ="Login";
    this.loginForm = this.fb.group({
      email_id: [null,Validators.compose([Validators.required,Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])],
      password:[null,Validators.compose([Validators.required,this.util.passwordLengthValidator()])]
    });
  }

  get loginFormControl()
  {
    return this.loginForm.controls;
  }


  public onSubmit() {
    if(this.loginForm.valid)
      console.log("VALID: CHECK IN DATABASE");
    else
      this.util.validateForm(this.loginForm);
  }
}
