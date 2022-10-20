import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(private fb:FormBuilder) { }

  page_heading: string = "";
  isEmailValid: boolean = true;
  isPasswordValid: boolean = true;
  registerForm: FormGroup = this.fb.group({});

  ngOnInit(): void 
  {
    this.page_heading = "Register"
    this.registerForm = this.fb.group
    (
    {
      user_name :[null],
      email_id:[null],
      phone_number:[null],
      password:[null]
    }
    );

    this.onEmailValueChange();
    this.onPasswordValueChange();
  }

  onEmailValueChange(): void
  {
    this.registerForm?.get("email_id")?.valueChanges.subscribe(()=>
    {
      this.isEmailValid = true;
    });
  }

  onPasswordValueChange(): void
  {
    this.registerForm?.get("password")?.valueChanges.subscribe(()=>
    {
      this.isPasswordValid = true;
    });
  }


  public isResgisterFormValid(form:any): boolean
  {
    let isValid:boolean = true;

    if(form.email_id === null || form.email_id === "")
    {
      this.isEmailValid = false,isValid= false;
    }
    else
    {
      //email validation
      let emailRegexPattern:RegExp =/^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$/;
      isValid = emailRegexPattern.test(form.email_id);
      if(!isValid)
      {
        this.isEmailValid = false;
      }
    }

    if(form.password === null || form.password === "")
    {
      this.isPasswordValid = false, isValid = false;
    }
    // password length validation
    else
    {
      if(form.password.length < 8 || form.password.length >15)
      {
        this.isPasswordValid = false, isValid = false;
      }
    }

    return isValid;
  }



  public onSubmit() 
  {
    console.log("onSubmit function " + this.registerForm.value);
    this.isResgisterFormValid(this.registerForm.value);
    }

}
