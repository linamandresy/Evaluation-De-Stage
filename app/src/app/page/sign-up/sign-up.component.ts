import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SignUpService } from 'src/app/service/sign-up.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent implements OnInit {
  error:string="";
  fname:string='';
  lname:string='';
  logins:string='';
  passwords:string='';
  constructor(private service:SignUpService,private router:Router) { }

  ngOnInit(): void {
  }
  signup():any{
    const onSuccess=(res:any)=>{
      if(res.status==200){
        this.router.navigate([""]);
      }else{
        this.error=res.data;
      }
    }
    const onError=(res:any)=>{
      this.router.navigate(["error",res.message]);
    }
    this.service.insertUser(this.fname,this.lname,this.logins,this.passwords).subscribe(onSuccess,onError);
  }
}
