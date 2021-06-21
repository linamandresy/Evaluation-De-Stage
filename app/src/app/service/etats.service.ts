import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class EtatsService {

  constructor(private http:HttpClient) { }
  insertEtats(nomEtat:string,budget:any,delai:any,coef:any,unite:any):Observable<any>{
    const data = new FormData();
    data.append("nomEtat",nomEtat);
    data.append("budget",budget);
    data.append("delai",delai);
    data.append("coef",coef);
    data.append("unitesDistances",unite)
    const opt={
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    }
    return this.http.post(base_url+"etats/insert",data,opt);
  }
  listEtats():Observable<any>{
    return this.http.get(base_url+"etats");
  }
  findById(id:number):Observable<any>{
    return this.http.get(base_url+"etats/"+id);
  }
  update(idEtat:any,nomEtat:any,budget:any,delai:any,coef:any,unit:any):Observable<any>{
    const data=new FormData();
    data.append("nomEtat",nomEtat);
    data.append("budget",budget);
    data.append("delai",delai);
    data.append("coef",coef);
    data.append("unitesDistances",unit);
    const opt = {
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    }
    return this.http.put(base_url+"etats/"+idEtat,data,opt);
  }
}
