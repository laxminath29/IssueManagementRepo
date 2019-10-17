import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { map, catchError } from 'rxjs/operators'
import { throwError } from 'rxjs';
import { NgxSpinnerService } from "ngx-spinner";
import {environment} from '../../environments/environment';
@Injectable({
  providedIn: 'root'
})
export class ApiService {
  url = environment.BASEURL;

  header: HttpHeaders;
  constructor(private http: HttpClient) { }
  getHeaders(optHeaders?: HttpHeaders) {
    let headers = new HttpHeaders();
    if (localStorage.getItem('LoggedInUser')) {
      headers = headers.set(
        'Authorization',
        'bearer ' + localStorage.getItem('LoggedInUser')
      );
    } else {
      headers = headers.set(
        'Authorization',
        'Basic ' + btoa('efkon-msil:nxtlife')
      );
    }
    if (optHeaders) {
      for (const optHeader of optHeaders.keys()) {
        headers = headers.append(optHeader, optHeaders.get(optHeader));
      }
    }
    return headers;
  }

  get(endpoint: string, optHeaders?: HttpHeaders) {
    const headers = this.getHeaders(optHeaders);
    return this.http
      .get(this.url + "/" + endpoint, { headers: headers, observe: "response" })
      .pipe(
        map(this.extractData),
        catchError(this.handleError)
      );
  }

  post(endpoint: string, body: any, optHeaders?: HttpHeaders) {
    const headers = this.getHeaders(optHeaders);
    return this.http
      .post(this.url + "/" + endpoint, body, {
        headers: headers,
        observe: "response"
      })
      .pipe(
        map(this.extractData),
        catchError(this.handleError)
      );
  }

  put(endpoint: string, body: any, optHeaders?: HttpHeaders) {
    const headers = this.getHeaders(optHeaders);
    return this.http
      .put(this.url + "/" + endpoint, body, {
        headers: headers,
        observe: "response"
      })
      .pipe(
        map(this.extractData),
        catchError(this.handleError)
      );
  }

  // delete(endpoint: string, optHeaders?: HttpHeaders) {
  //   const headers = this.getHeaders(optHeaders);
  //   return this.http
  //     .delete(this.url + "/" + endpoint, {
  //         headers:headers,
  //       observe: "response"
  //     })
  //     .pipe(
  //       map(this.extractData),
  //       catchError(this.handleError)
  //     );
  // }
  // another method which will use for operations
  extractData = (response: HttpResponse<any>) => {
    if (response.status === 204) {
      // this.showalert("Data not Found");
      console.log("Data not found");

    }
    return response.body || response.status;
  }
  // error handle method
  handleError(errorResponse: HttpErrorResponse) {


    if (errorResponse.status)
      // this.showalert(errorResponse.error.message || "Something went wrong");
      console.log(errorResponse.error.message || "Something went wrong");
    alert(errorResponse.error.message || "Something went wrong");

    return throwError(errorResponse);

    // if (errorResponse.error instanceof ErrorEvent) {
    // this.showalert(errorResponse.error.message || "somthing goes wrong!");
    //   console.log("client side error", errorResponse.error.message);
    // } else {
    // this.showalert(errorResponse || "server side error");
    //   console.log("server side error", errorResponse);
    // }
    // return throwError("server side problem ! No data found");
  }
  // alert method
  showalert(message) {
    console.log(message);

  }

  // showSpinner(){
  //   this.spinner.show("please wait", {
  //     type: "line-scale-party",
  //     size: "large",
  //     bdColor: "rgba(100,149,237, .8)",
  //     color: "white"
  //   });
  // }
  // hideSpinner(){
  //   this.spinner.hide("please wait")
  // }
}