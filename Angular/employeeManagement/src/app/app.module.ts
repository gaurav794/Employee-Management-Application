import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './component/login/login.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { LogoutComponent } from './component/logout/logout.component';
import { PageHeadingComponent } from './component/page-heading/page-heading.component';

@NgModule({
  declarations: [
    LoginComponent,
    DashboardComponent,
    LogoutComponent,
    PageHeadingComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [LoginComponent]
})
export class AppModule { }
