import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { VilleService } from 'src/app/service/ville.service';

@Component({
  selector: 'app-villeinsert',
  templateUrl: './villeinsert.component.html',
  styleUrls: ['./villeinsert.component.scss']
})
export class VilleinsertComponent implements OnInit {
  nomVilles:string='';
  constructor(private service:VilleService,private router:Router) { }

  ngOnInit(): void {
  }
  insert():any{
    const onSuccess=(res:any)=>{
      if(res.status==200){
        this.router.navigate(["admin"]);
      }else{
        this.router.navigate(["error",res.data]);
      }
    }
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    }
    this.service.insertVilles(this.nomVilles).subscribe(onSuccess,onError);
  }

}
