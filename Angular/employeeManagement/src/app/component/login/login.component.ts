import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  public page_heading: string = "Login";

  public login_form:FormGroup = this.fb.group({
    email_id: [null,Validators.compose([Validators.required])],
    password:[null,Validators.compose([Validators.required])]
  });

  get email_id(){return this.login_form.get("email_id")}
  get password(){return this.login_form.get("password")}

  public onSubmit() {
    console.log(this.login_form.value);
  }
}
