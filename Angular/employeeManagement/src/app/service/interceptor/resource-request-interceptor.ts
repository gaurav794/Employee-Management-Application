import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ResourceRequestInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    //Authorized user only
    const bearerToken = sessionStorage?.getItem('id_token');
    const headers = new HttpHeaders({
      Authorization: `Bearer ${bearerToken}`,
    });

    const request = req.clone({ headers: headers });

    return next.handle(request);
  }
}
