import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Spark } from '../models/spark.model';
import { map } from 'rxjs/operators';
import { environment as env } from '../enviroments/environment';
import { CookieService } from 'ngx-cookie-service';
import { environment } from '../enviroments/environment';

@Injectable({
  providedIn: 'root'
})
export class SparkService {

  authToken: string = "";
  apiUrl: string = environment.apiUrl;

  constructor(
    private http: HttpClient,
    private cookie: CookieService
  ) { }

  protected defaultHeaders = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  protected authorizationHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + this.authToken
  });

  loadAuthToken() {
    this.authToken = this.cookie.get('accessToken');
    if (this.authToken == '' || this.authToken == undefined) {
      alert('You are not logged in');
    }
    this.authorizationHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + this.authToken
    });
  }

  getListOfSparks(): Observable<Spark[]> {
    return this.http.get(this.apiUrl + '/sparks', { headers: this.defaultHeaders }).pipe(
      map((body: any) => body.map((n: any) => Spark.fromObject(n)))
    );
  }

  createNewSpark(spark: Spark) {
    this.loadAuthToken();
    return this.http.post(this.apiUrl + '/sparks', spark.content, { headers: this.authorizationHeaders });
  }

  updateSpark(spark: Spark) {
    this.loadAuthToken();
    return this.http.put(this.apiUrl + `/sparks?id=${spark.id}`, spark.content, { headers: this.authorizationHeaders });
  }

  deleteSpark(spark: Spark) {
    this.loadAuthToken();
    return this.http.delete(this.apiUrl + `/sparks?id=${spark.id}`, { headers: this.authorizationHeaders });
  }

  updateLightCount(spark: Spark) {
    this.loadAuthToken();
    return this.http.put(this.apiUrl + `/weigh?id=${spark.id}`, null, { headers: this.authorizationHeaders });
  }
}
