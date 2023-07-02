/**
 * Service for authentification related backend communication.
 */
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User, UserAuth } from '../models/user.model'
import { environment } from '../enviroments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  userBackend = environment.baseUrl;

  /**
   * Registers a new User in the Database
   * @param user Userdata to store
   * @returns ok = true if User was successfully registerd or ok = false if not
   */
  registerUser(user: User) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(this.userBackend + '/auth/register', user, { headers: headers, responseType: 'json' })
  }

  /**
   * Authenticates a users username and password
   * @param user Login data
   * @returns 
   */
  authenticateUser(user: UserAuth) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return this.http.post(this.userBackend + '/auth/login', user, { headers: headers, responseType: 'json' })
  }


}
