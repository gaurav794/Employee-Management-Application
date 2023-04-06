import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpErrorResponse,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { ToastService } from '../toast/toast.service';

@Injectable({ providedIn: 'root' })
export class ErrorHandlerInterceptor implements HttpInterceptor {
  constructor(private toastService: ToastService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      tap((error) => {
        if (error instanceof HttpErrorResponse) {
          this.toastService.show(error.name, error.message, 5000);
        }
      })
    );
  }
}
