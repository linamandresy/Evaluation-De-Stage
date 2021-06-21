import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EtatsService } from 'src/app/service/etats.service';

@Component({
  selector: 'app-etat-update',
  templateUrl: './etat-update.component.html',
  styleUrls: ['./etat-update.component.scss']
})
export class EtatUpdateComponent implements OnInit {
  idEtat:any='';
  nomEtat:any='';
  budget:any='';
  delai:any='';
  coef:any='';
  unit:any='';
  
  error:string='';
  constructor(
    private route:ActivatedRoute,
    private service:EtatsService,
    private router:Router) { }

  ngOnInit(): void {
    const onSuccess=(res:any)=>{
      if(res.status==200) {
        this.idEtat=res.data.idEtats;
        this.nomEtat=res.data.nomEtat;
        this.budget=res.data.budget;
        this.delai=res.data.delai;
        this.coef=res.data.coef;
        this.unit=res.data.unitesDistances;
      }else
        this.error=res.data;
    };
    const onError=(res:any)=>{
      this.router.navigate(['error'],res.message);
    };
    this.route.params.subscribe(params=>{
      this.idEtat=params['id'];
      this.service.findById(this.idEtat).subscribe(onSuccess,onError);
    });
  }
  save():any{
    const onSuccess=(res:any)=>{
      if(res.status==200) this.router.navigate(["etats"])
      else this.error=res.data;
    };
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    };
    this.service.update(this.idEtat,this.nomEtat,this.budget,this.delai,this.coef,this.unit).subscribe(onSuccess,onError);
  }
}
