import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PortionsService {

  constructor(private http:HttpClient) { }
  insertPortions(idRoute:any,debut:any,fin:any,idEtat:any):Observable<any>{
    const opt = {
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    };
    const data = new FormData();
    data.append("idRoutes",idRoute);
    data.append("debut",debut);
    data.append("arrive",fin);
    data.append("idEtats",idEtat);
    return this.http.post(base_url+"portions/insert",data,opt);
  }
}
