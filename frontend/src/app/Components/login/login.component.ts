import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { filter, Subject, takeUntil } from 'rxjs';

import { CookieService } from 'ngx-cookie-service';

import { AuthService } from '../../services/auth.service';
import { UserAuth } from 'src/app/models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  userAuth: UserAuth = {
    username: '',
    password: ''
  }

  isUserLoggedIn: boolean = false;
  private readonly _destroy = new Subject<void>();

  constructor(
    private router: Router,
    private authService: AuthService,
    private cookie: CookieService
  ) { }

  ngOnInit(): void {
  }

  ngOnDestroy(): void {
    this._destroy.next(undefined);
    this._destroy.complete;
  }

  validateForm(): boolean {
    let valid: boolean = true;

    if ("" == this.userAuth.username.trim()) {
      document.getElementById("usernameField")!.className = "form-control is-invalid";
      valid = false;
    } else {
      document.getElementById("usernameField")!.className = "form-control form-field";
    }

    if ("" == this.userAuth.password.trim()) {
      document.getElementById("passwordField")!.className = "form-control is-invalid";
      valid = false;
    } else {
      document.getElementById("passwordField")!.className = "form-control form-field";
    }

    return valid;
  }

  onLoginSubmit() {
    if (!this.validateForm()) {
      return;
    }

    this.authService.authenticateUser(this.userAuth)
      .pipe(takeUntil(this._destroy))
      .subscribe({
        next: (response: any) => {
          var token = response.accessToken;
          this.cookie.set('accessToken', token);
          var role = response.userRole;
          this.cookie.set('role', role);
          var username = this.userAuth.username;
          this.cookie.set('username', username);
          this.isUserLoggedIn = true;
          this.router.navigate(['/view-sparks']);
        },
        error: (error: any) => {
          error = error.error;
          console.log(error);
          alert(error.message);
        }
      });
  }
}
