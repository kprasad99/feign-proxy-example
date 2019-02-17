import { Component, OnInit, Inject } from '@angular/core';
import { MAT_SNACK_BAR_DATA } from '@angular/material';


export interface ToastMessage {
  type: string;
  icon: string;
  detail: string;
  title: string;
}


@Component({
  selector: 'k-toast',
  templateUrl: './toast.component.html',
  styleUrls: ['./toast.component.css']
})
export class ToastComponent implements OnInit {

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: ToastMessage) { }

  ngOnInit() {
  }

}
