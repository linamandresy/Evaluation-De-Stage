import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouteService } from 'src/app/service/route.service';

@Component({
  selector: 'app-routes-liste',
  templateUrl: './routes-liste.component.html',
  styleUrls: ['./routes-liste.component.scss']
})
export class RoutesListeComponent implements OnInit {
  error:string='';
  routes:any=[];
  constructor(private service:RouteService,private router:Router) { }

  ngOnInit(): void {
    const onSuccess=(res:any)=>{
      if(res.status==200) this.routes=res.data;
      else this.error=res.data;
    };
    const onError=(res:any)=>{
      this.router.navigate(["error"],res.message);
    };
    this.service.listLabeledRoute().subscribe(onSuccess,onError);
  }

}
