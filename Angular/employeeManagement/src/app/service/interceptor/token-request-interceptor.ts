import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { auth_server } from 'src/environments/environment';
import { Buffer } from 'buffer';

export class TokenRequestInterceptor implements HttpInterceptor {
  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const Authorization =
      `Basic ` +
      Buffer.from(`${auth_server.CLIENT_ID}:${auth_server.CLIENT_SECRET}`);

    let reqClone = req.clone({ setHeaders: { Authorization } }); // modify the cloned version

    return next.handle(reqClone);
  }
}
