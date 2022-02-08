import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Apply } from '../models/request/apply.model';
import { Job } from '../models/response/job.model';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  constructor(
    private http: HttpClient,
  ) { }

  getAll(): Observable<Job[]> {
    return this.http.get<Job[]>(`${environment.api_url}jobs`, { responseType: 'json' });
  }

  /*post(body: Apply): Observable<string> {
    return this.http.post(`${environment.api_url}elastic/upload-index`, body, {
      headers: new HttpHeaders({
        'Content-Type': 'multipart/form-data; boundary=<calculated when request is sent>',
      }),
      responseType: 'text'
    });
  }*/
  post(body: any, options: any): Observable<any> {
    return this.http.post(`${environment.api_url}elastic/upload-index`, body, options);
  }
}
