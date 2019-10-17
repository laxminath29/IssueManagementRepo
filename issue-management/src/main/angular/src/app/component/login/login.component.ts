import { Component, OnInit } from '@angular/core';
import {Login}from './login.model';
import { NgModel, NgForm } from '@angular/forms';
import { LoginService } from 'src/app/services/login/login.service';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  inputForm:Login ={
    email:'',
    password:''
  }
  constructor(private loginService:LoginService,private auth:AuthService,private router:Router) { }

  ngOnInit() {
  }

  onSubmit(formData:NgForm){
      console.log(formData.value);
      // api call here
    this.loginService.login().subscribe((token)=>{
        console.log("the token is ",token);
        console.log(token.access_token);
        this.auth.sendToken(token.access_token);
        if (this.auth.isLoggedIn()) {
            this.router.navigate(['dashboard']);
        }else{
          this.router.navigate(['login']);
        }
         
    });
  }
}
