import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminloginService } from 'src/app/service/adminlogin.service';

@Component({
  selector: 'app-adminlogin',
  templateUrl: './adminlogin.component.html',
  styleUrls: ['./adminlogin.component.scss']
})
export class AdminloginComponent implements OnInit {
  logins:string='';
  passwords:string='';
  error:string='';
  constructor(
    private service:AdminloginService,
    private router:Router
  ) { }

  ngOnInit(): void {
  }
  login():void{
    
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
