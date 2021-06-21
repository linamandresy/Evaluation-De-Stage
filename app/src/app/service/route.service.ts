import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class RouteService {

  constructor(private http: HttpClient) { }
  insertRoutes(noRn: any, idDepart: any, idArrive: any, distance: any): Observable<any> {
    const data = new FormData();
    data.append("noRn", noRn);
    data.append("idVilleDepart", idDepart);
    data.append("idVilleArrive", idArrive);
    data.append("distance", distance);
    const opt = {
      headers: {
        'Authorization': 'Bearer ' + sessionStorage.getItem('token')
      }
    };
    return this.http.post(base_url + "routes", data, opt);
  }
}
