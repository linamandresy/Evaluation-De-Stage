import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EtatsService } from 'src/app/service/etats.service';
import { PortionsService } from 'src/app/service/portions.service';

@Component({
  selector: 'app-portions-update',
  templateUrl: './portions-update.component.html',
  styleUrls: ['./portions-update.component.scss']
})
export class PortionsUpdateComponent implements OnInit {
  idRoute:number=0;
  idPortions:number=0;
  etats:any=[];
  error:string='';
  distance:number=0;
  idEtat:number=0;
  constructor(
    private es:EtatsService,
    private router:Router,
    private route:ActivatedRoute,
    private service:PortionsService) { }

  ngOnInit(): void {
    const onSuccess=(res:any)=>{
      if(res.status==200){
        this.distance=res.data.distance;
        this.idEtat=res.data.idEtats;
        console.log(res);
      }else{
        this.error=res.data;
      }
    }
    const onError=(res:any)=>{
      this.router.navigate(['error',res.message])
    }
    this.es.listEtats().subscribe((res:any)=>{
      if(res.status==200)
        this.etats=res.data;
      else this.error=res.data;
    },(err:any)=>{
      this.router.navigate(["error",err.message]);
    });
    this.route.params.subscribe(params=>{
      this.idRoute=params['id'];
      this.idPortions=params['idP'];
      this.service.findById(this.idPortions).subscribe(onSuccess,onError);
    });
  }
  update():any{
    this.service.update(this.idPortions,this.idRoute,this.distance,this.idEtat).subscribe((res:any)=>{
      if(res.status==200){
        this.router.navigate(['/routes',this.idRoute,'portion']);
      }else{
        this.error=res.data;
      }
    },(err:any)=>{
      this.router.navigate(['error',err.message]);
    });
  }
}
