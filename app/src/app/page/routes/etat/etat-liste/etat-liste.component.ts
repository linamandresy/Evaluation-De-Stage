import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EtatsService } from 'src/app/service/etats.service';

@Component({
  selector: 'app-etat-liste',
  templateUrl: './etat-liste.component.html',
  styleUrls: ['./etat-liste.component.scss']
})
export class EtatListeComponent implements OnInit {

  constructor(private service:EtatsService,private router:Router) { }
  etat:any=[];
  ngOnInit(): void {
    const onSuccess=(res:any)=>{
      if(res.status==200)  this.etat=res.data;
      else this.router.navigate(['error',res.data]);
    };
    const onError=(res:any)=>{
      this.router.navigate(['error',res.message]);
    };
    this.service.listEtats().subscribe(onSuccess,onError);
  }
}
