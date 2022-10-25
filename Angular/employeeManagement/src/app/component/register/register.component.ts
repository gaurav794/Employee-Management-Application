import { Component, OnInit } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { AbstractControl, FormBuilder, FormGroup, Validators, ValidatorFn, ValidationErrors, FormControl } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  page_heading: string = "";
  registerForm: FormGroup = this.fb.group({});
  confirmPassword = new FormControl();

  ngOnInit(): void {
    this.page_heading = "Register"
    this.registerForm = this.fb.group
      (
        {
          user_name: [null, Validators.required],
          email_id: [null, Validators.compose([Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")])],
          phone_number: [null, Validators.required],
          password: [null, Validators.required]
        }
      );
    this.confirmPassword = new FormControl(null, Validators.compose([Validators.required, this.match]));

  }

  match(confirm: AbstractControl): { [key: string]: any } | null {

    let pass: string = this.registerForm.get('password')?.value;
    let confirmPassword: string = confirm.value;
    const isMatch = pass === confirmPassword;
    return isMatch ? null : { isMatch: true };
  }

  get registerFormControl() {
    return this.registerForm.controls;
  }


  public onSubmit() {
    console.log(this.confirmPassword);
  }

}

