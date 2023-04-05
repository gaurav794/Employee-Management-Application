import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResourceRequestInterceptor } from '../interceptor/resource-request-interceptor';
import { TokenRequestInterceptor } from '../interceptor/token-request-interceptor';
import { environment } from 'src/environments/environment';

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
    let url: string =
      'http://localhost:8080/oauth2/token?client_id=client&redirect_uri=http://127.0.0.1:4200/authorized&grant_type=authorization_code&code=&code_verifier=qPsH306-ZDDaOE8DFzVn05TkN3ZZoVmI_6x4LsVglQI';
    url = url.replace('code=', 'code=' + this.code);
    console.log(url);

    return this.http.post(url, null); // get id_token
  }
}
