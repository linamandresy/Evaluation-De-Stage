import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class VilleService {

  constructor(private http: HttpClient) { }
  insertVilles(nom: string): Observable<any> {
    const data = new FormData();
    data.append("nomVilles", nom);
    const opt = {
      headers: {
        'Authorization': 'Bearer ' + sessionStorage.getItem("token")
      }
    };
    return this.http.post(base_url + "villes", data, opt);
  }
  getVilles():Observable<any>{
    return this.http.get(base_url+"villes");
  }
}
