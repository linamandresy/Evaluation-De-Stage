import { ThrowStmt } from '@angular/compiler';
import { Component, Input, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  logins:string='';
  passwords:string='';
  error:string='';
  constructor(
    private service:LoginService,
    private router:Router
    ) { }

  ngOnInit(): void {
  }
  login(): void { 
    const onSuccess=(res:any)=>{
      if(res.status==200){
        console.log(res);
        sessionStorage.setItem("token",res.data);
        this.router.navigate([""]);
      }else{
        this.error=res.data;
      }
    }
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    }
    this.service.authentificate(this.logins,this.passwords).subscribe(onSuccess,onError);
  }

}
