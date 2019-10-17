import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { Login } from 'src/app/component/login/login.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private api:ApiService) { }

   login(){
     return this.api.post('oauth/token?grant_type=password&username=efkon&password=abc123',
     {});
  }
}
