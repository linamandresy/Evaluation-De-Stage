import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-error404',
  templateUrl: './error404.component.html',
  styleUrls: ['./error404.component.scss']
})
export class Error404Component implements OnInit {
  error:string='';
  constructor(private route:ActivatedRoute) { }

  ngOnInit(): void {
    try {
      this.route.params.subscribe(params=>{
        this.error=params['message'];
      })
      
    } catch (error) {}
  }

}
