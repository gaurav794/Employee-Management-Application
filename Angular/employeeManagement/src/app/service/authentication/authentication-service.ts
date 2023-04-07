import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
// import { ResourceRequestInterceptor } from '../interceptor/resource-request-interceptor';
// import { TokenRequestInterceptor } from '../interceptor/token-request-interceptor';
import { Buffer } from 'buffer';
import {
  environment,
  auth_server,
  tokenURL,
} from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private code: string = '';

  constructor(private http: HttpClient) {}

  public setCode(code: string) {
    this.code = code;
  }

  public getCode() {
    return this.code;
  }

  getToken() {
    let url: string = tokenURL(this.code);
    //HTTP POST
    return this.http.post(url, null); // get id_token
  }
}
