import { formatNumber } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit 
{

  isEmailValid: boolean= true;
  emailValidationMsg:string = "";
  isPasswordValid: boolean = true;
  passwordValidationMsg:string = "";
  page_heading: string ="";
  login_form:FormGroup = this.fb.group({});

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void 
  {
    this.login_form = this.fb.group({
      email_id: [null],
      password:[null]
    });

    this.onEmailValueChange();
    this.onPasswordValueChange();

  }

  onEmailValueChange(): void
  {
    this.login_form?.get("email_id")?.valueChanges.subscribe(()=>
    {
      this.isEmailValid = true;
    });
  }

  onPasswordValueChange(): void
  {
    this.login_form?.get("password")?.valueChanges.subscribe(()=>
    {
      this.isPasswordValid = true;
    });
  }

  public isloginFormValid(form:any)
  {
    console.log(form);
    let isValid:boolean = true;
    if(form.email_id === null || form.email_id === "")
    {
      this.emailValidationMsg = "Email ID is required";
      this.isEmailValid = false;
      isValid= false;
    }
    else
    {
      //email validation
      let emailRegexPattern:RegExp =/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
      isValid = emailRegexPattern.test(form.email_id);
      if(!isValid)
      {
        this.emailValidationMsg = "Enter valid email address";
        this.isEmailValid = false;
      }
    }
    if(form.password === null || form.password === "")
    {
      this.passwordValidationMsg = "Password is required";
      this.isPasswordValid = false, isValid = false;
    }
    // password length validation
    else
    {
      if(form.password.length < 8 || form.password.length >15)
      {
        console.log(form.password.length);
        this.passwordValidationMsg = "Password length should be greater than 8 and less than 15";
        this.isPasswordValid = false, isValid = false;
      }
    }

    return isValid;
  }

  public onSubmit() {

    //Validation check
    let isValid = this.isloginFormValid(this.login_form.value);
  }
}
