import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})
export class LogoutComponent implements OnInit {
  constructor() {}

  ngOnInit(): void {}

  logout() {
    //removes id_token and refresh_token for the current user
    sessionStorage.clear();
  }
}
