import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from 'src/app/service/authentication/authentication-service';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { HttpParams } from '@angular/common/http';
import { Token } from '@angular/compiler';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css'],
})
export class AuthenticationComponent implements OnInit {
  constructor(
    private authenticationService: AuthenticationService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.getAuthorizationCode();
  }

  ngOnInit(): void {
    this.authenticationService
      .getToken()
      .pipe(take(1))
      .subscribe((tokens) => {
        if ((tokens as any)?.id_token) {
          sessionStorage.setItem('id_token', (tokens as any).id_token);
          sessionStorage.setItem(
            'refresh_token',
            (tokens as any).refresh_token
          );
          //redirect to dashboard
          this.router.navigateByUrl('');
        } else {
          console.error('Forbidden');
        }
      });
  }

  getAuthorizationCode() {
    this.activatedRoute.queryParams.subscribe((params) => {
      if (params?.['code']) {
        let code: string = params['code'];
        console.log(code);
        this.authenticationService.setCode(code);
      }
    });
  }
}
