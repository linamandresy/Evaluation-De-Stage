import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EtatsService } from 'src/app/service/etats.service';
import { PortionsService } from 'src/app/service/portions.service';

@Component({
  selector: 'app-portions-insert',
  templateUrl: './portions-insert.component.html',
  styleUrls: ['./portions-insert.component.scss']
})
export class PortionsInsertComponent implements OnInit {
  idRoute: any='';
  distance: any='';
  idEtat: any='';
  etats: any = []
  error: string = '';
  constructor(
    private route: ActivatedRoute,
    private es: EtatsService,
    private service: PortionsService,
    private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idRoute = params['id'];
    });
    const onSuccess = (res: any) => {
      if (res.status == 200)
        this.etats = res.data;
      else
        this.error = res.data;
    }
    this.es.listEtats().subscribe(onSuccess);
  }
  save(): any {
    const onSuccess = (res: any) => {
      if (res.status == 200) this.router.navigate(["routes",this.idRoute,"portion"]);
      else this.error = res.data;
    };
    const onError = (res: any) => {
      this.router.navigate(["error", res.message]);
    };
    this.service.insertPortions(this.idRoute, this.distance, this.idEtat).subscribe(onSuccess, onError);
  }
}
