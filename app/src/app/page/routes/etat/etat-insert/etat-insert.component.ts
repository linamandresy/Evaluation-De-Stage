import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EtatsService } from 'src/app/service/etats.service';

@Component({
  selector: 'app-etat-insert',
  templateUrl: './etat-insert.component.html',
  styleUrls: ['./etat-insert.component.scss']
})
export class EtatInsertComponent implements OnInit {
  nomEtat: string = '';
  budget: any;
  delai: any;
  coef: any;
  unit:any;
  error: string = '';
  constructor(private service: EtatsService, private router: Router) { }

  ngOnInit(): void {
  }
  save(): any {
    const onSuccess = (res: any) => {
      if (res.status == 200)
        this.router.navigate(["etats"]);
      else
        this.error = res.data;
    };
    const onError = (res: any) => {
      this.router.navigate(["error", res.message]);
    };
    this.service.insertEtats(this.nomEtat, this.budget, this.delai, this.coef,this.unit).subscribe(onSuccess, onError);
  }
}
