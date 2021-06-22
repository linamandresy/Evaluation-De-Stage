import { Component, NO_ERRORS_SCHEMA, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouteService } from 'src/app/service/route.service';
import { VilleService } from 'src/app/service/ville.service';
declare var $:any;

@Component({
  selector: 'app-routes-insert',
  templateUrl: './routes-insert.component.html',
  styleUrls: ['./routes-insert.component.scss']
})
export class RoutesInsertComponent implements OnInit {
  noRn:number=0;
  idDepart:number=0;
  idArrive:number=0;
  distance:number=0.0;
  villes:any=[];
  error :string='';
  constructor(private vs:VilleService,private service:RouteService,private router:Router) { }

  ngOnInit(): void {
    const onSuccess = (res:any)=>{
      if(res.status==200){
        this.villes = res.data;
      }
    };
    this.vs.getVilles().subscribe(onSuccess);
  }
  save():any{
    if(this.idArrive==this.idDepart){
      this.error="Ville de départ et ville d'arrivée identique";
      return;
    }
    const onSuccess=(res:any)=>{
      if(res.status==200){
        this.router.navigate(["routes"]);
      }else{
        this.error=res.data;
      }
    };
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    };
    this.service.insertRoutes(this.noRn,this.idDepart,this.idArrive,this.distance).subscribe(onSuccess,onError);
  }
}
