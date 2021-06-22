import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PortionsService {

  constructor(private http: HttpClient) { }
  insertPortions(idRoute: any, distance: any, idEtat: any): Observable<any> {
    const opt = {
      headers: {
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      }
    };
    const data = new FormData();
    data.append("idRoutes", idRoute);
    data.append("distance", distance);
    data.append("idEtats", idEtat);
    return this.http.post(base_url + "portions/insert", data, opt);
  }
  getLabel(idRoute: number): Observable<any> {
    return this.http.get(base_url + "portions/label/" + idRoute);
  }
  valider(idRoute:number):Observable<any>{
    const opt = {
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    }
    return this.http.put(base_url+"routes/valider/"+idRoute,null,opt);
  }
  findById(idPortion:number):Observable<any>{
    return this.http.get(base_url+"portions/find/"+idPortion);
  }
  update(idPortion:number,idRoutes:any,distance:any,idEtats:any):Observable<any>{
    const opt = {
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    };
    const data = new FormData();
    data.append("idRoutes",idRoutes);
    data.append("distance",distance);
    data.append("idEtats",idEtats);
    return this.http.put(base_url+"portions/update/"+idPortion,data,opt);    
  }
}
