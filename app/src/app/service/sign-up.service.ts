import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { base_url } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SignUpService {

  constructor(private http:HttpClient) { }
  insertUser(fname:string,lname:string,logins:string,passwords:string):Observable<any>{
    const data=new FormData();
    data.append("fName",fname);
    data.append("lName",lname);
    data.append("logins",logins);
    data.append("passwords",passwords);
    const opt={
      headers:{
        'Authorization':'Bearer '+sessionStorage.getItem('token')
      }
    }
    return this.http.post(base_url+"users/signup",data,opt);
  }
}
