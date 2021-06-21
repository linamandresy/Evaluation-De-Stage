import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PortionsService } from 'src/app/service/portions.service';

@Component({
  selector: 'app-portions-liste',
  templateUrl: './portions-liste.component.html',
  styleUrls: ['./portions-liste.component.scss']
})
export class PortionsListeComponent implements OnInit {
  idRoute:any='';
  error:string='';
  portions:any=[];
  constructor(
    private route:ActivatedRoute,
    private service:PortionsService,
    private router:Router) { }

  ngOnInit(): void {
    const onSuccess=(res:any)=>{
      if(res.status==200) this.portions=res.data;
      else this.error=res.data; 
    };
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    };
    this.route.params.subscribe(params => {
      this.idRoute = params['id'];
      this.service.getLabel(this.idRoute).subscribe(onSuccess,onError);
    });
  }
}
