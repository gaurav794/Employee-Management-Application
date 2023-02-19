import { Component, OnInit } from '@angular/core';
import { NgbProgressbarModule } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-progress-bar',
  templateUrl: './progress-bar.component.html',
  styleUrls: ['./progress-bar.component.css'],
  standalone: true,
  imports: [NgbProgressbarModule]
})
export class ProgressBarComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
