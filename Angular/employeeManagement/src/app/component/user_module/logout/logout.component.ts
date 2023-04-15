import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { auth_server } from 'src/environments/environment';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})
export class LogoutComponent implements OnInit {
  constructor(private http: HttpClient) {}

  ngOnInit(): void {}

  logout() {
    //removes id_token and refresh_token for the current user
    this.http.post(`${auth_server.URL}/logout`, null).subscribe();
    sessionStorage.clear();
    //Redirect to Authorization Server Login Page
    window.location.href = `${auth_server}/login`;
  }
}
