import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RouteService } from 'src/app/service/route.service';
declare var $:any;

@Component({
  selector: 'app-routes-liste',
  templateUrl: './routes-liste.component.html',
  styleUrls: ['./routes-liste.component.scss']
})
export class RoutesListeComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  error:string='';
  routes:any=[];
  constructor(private service:RouteService,private router:Router) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    const onSuccess=(res:any)=>{
      if(res.status==200) {
        this.routes=res.data;
        $(function(){
          $("#route-table").DataTable();
        });
      }
      else this.error=res.data;
    };
    const onError=(res:any)=>{
      this.router.navigate(["error"],res.message);
    };
    this.service.listLabeledRoute().subscribe(onSuccess,onError);
  }
  download():any{
    this.service.download();
  }
}
