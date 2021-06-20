import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { base_url } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http:HttpClient) { }
  authentificate(login:string,password:string):any{
    const data=new FormData();
    data.append("logins",login);
    data.append("passwords",password);
    return this.http.post(base_url+"users/login",data);
  }
}
