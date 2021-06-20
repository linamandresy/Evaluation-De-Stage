import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AdminloginService {

  constructor(private http:HttpClient) { }
  authentificate(login:string,password:string):any{
    const data = new FormData();
    data.append("logins",login);
    data.append("passwords",password);
    return this.http.post(base_url+"admins/login",data);
  }
}
