import { Injectable } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn } from '@angular/forms';
import { RegisterComponent } from '../register/register.component';

@Injectable({
  providedIn: 'root'
})
export class FormValidatorService {

  constructor() { }

  matchPassword() {
    return (form: FormGroup): any | null => {

      let pass: string = form.controls['password']?.value;
      let confirmPassword: string = form.controls['confirmPassword']?.value;

      if (pass == null || pass == '' || confirmPassword == null || confirmPassword == '') return null;
      if (pass != null && confirmPassword == null) return null;

      const isMatch = pass === confirmPassword;

      if (!isMatch)
        form.controls['confirmPassword'].setErrors({ isMatch: true });
      else {
        form.controls['confirmPassword'].setErrors(null);
      }
    }
  }

  passwordLengthValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let password:string = control?.value;
      if(password === '') return null;

      let length = password?.length;

      if (length < 8)
        return { lessLength: true };
      if (length > 15)
        return { greaterLength: true };

      return null;
    }
  }

  phoneNumberValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      let phoneNumber:string = control?.value;
      if(phoneNumber === '') return null;

      let length = phoneNumber?.length;
      
      if (length < 10)
        return { lessDigit: true };

      return null;
    }
  }

  validateForm(registerForm: FormGroup) {
    for (const field in registerForm.controls) {
      let ctrlValue: string = registerForm.controls[field]?.value;
      if (ctrlValue == null)
        registerForm.controls[field].markAsTouched();
    }
  }
}
