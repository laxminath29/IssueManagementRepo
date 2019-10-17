import { Injectable } from '@angular/core';
import { ApiService } from '../api.service';
import { HttpClient } from '@angular/common/http';
import { Dashboard } from 'src/app/component/dashboard/dashboard';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

    url= "http://192.168.1.43:8000"
  constructor(private api:ApiService,private http:HttpClient) { }

  updateDashboard(body:any,id:number){
    return this.api.put(`users/${id}`, body);
  }

  getDashboardData(){
    return this.api.get(`incidents/support/all`);
  }

  RemarkPut(id,body:Dashboard){
    return this.api.put(`incidents/v1/${id}`,body);
  }

  downloadCSV(){
    return this.api.get('incidents/support/all/excel');
  }
  // showspinner(){
  //   this.api.showSpinner();
  // }
  // hidespinner(){
  //   this.api.hideSpinner();
  // }
}
