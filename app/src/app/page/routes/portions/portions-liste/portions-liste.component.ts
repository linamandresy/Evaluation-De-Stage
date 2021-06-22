import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PortionsService } from 'src/app/service/portions.service';
declare var $:any;
@Component({
  selector: 'app-portions-liste',
  templateUrl: './portions-liste.component.html',
  styleUrls: ['./portions-liste.component.scss']
})
export class PortionsListeComponent implements OnInit {
  idRoute:any='';
  error:string='';
  portions:any=[];
  noRn:number=0;
  delai:number=0;
  cout:number=0;
  dtOptions: DataTables.Settings = {};
  valid:any='';
  distance:number=0;
  sommeProp:number=0;
  nomVilleDepart:string='';
  nomVilleArrive:string='';
  part:any=[];
  color:any=[];
  constructor(
    private route:ActivatedRoute,
    private service:PortionsService,
    private router:Router) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    const onSuccess=(res:any)=>{
      if(res.status==200) {
        console.log(res);
        this.portions=res.data.listPortions;
        $(function(){
          $("#portion-table").DataTable();
        });
        this.noRn=res.data.noRn;
        this.delai=res.data.delaiTotal;
        this.cout=res.data.montantTotal;
        this.distance=res.data.distance;
        this.valid = res.data.valid;
        this.sommeProp=res.data.sumDistance;
        this.nomVilleDepart=res.data.villeDebut;
        this.nomVilleArrive=res.data.villeFin;
        this.part=res.data.part;
        this.color=res.data.color;
        console.log(this.part);
      }
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
  valider():any{
    if(this.distance!=this.sommeProp){
      this.error="Distance incohérente ! Veuillez modifier la liste des proportions";
      return;
    }
    const onSuccess=(res:any)=>{
      if(res.status==200){
        this.router.navigate(['routes']);
      }else{
        this.error=res.data;
      }
    }
    const onError=(res:any)=>{
      this.router.navigate(['error',res.message]);
    }
    this.service.valider(this.idRoute).subscribe(onSuccess,onError);
  }
}
