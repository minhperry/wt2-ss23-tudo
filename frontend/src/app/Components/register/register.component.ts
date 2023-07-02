import { Component, OnInit } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router'

import { User, UserType } from '../../models/user.model'
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  showPwRequirements: boolean = false;

  newUser: User = {
    id: null,
    name: "",
    username: "",
    password: "",
    usertype: UserType.USER
  }

  ngOnInit(): void {
  }

  validateForm(): boolean {
    let valid: boolean = true;

    if ("" == this.newUser.name.trim()) {
      document.getElementById("nameField")!.className = "form-control is-invalid";
      valid = false;
    } else {
      document.getElementById("nameField")!.className = "form-control form-field";
    }

    if ("" == this.newUser.username.trim()) {
      document.getElementById("usernameField")!.className = "form-control is-invalid";
      valid = false;
    } else {
      document.getElementById("usernameField")!.className = "form-control form-field";
    }

    if ("" == this.newUser.password.trim()) {
      document.getElementById("passwordField")!.className = "form-control is-invalid";
      valid = false;
    } else {
      document.getElementById("passwordField")!.className = "form-control form-field";
    }

    return valid;
  }

  onRegisterSubmit() {

    if (!this.validateForm()) {
      return;
    }

    this.authService.registerUser(this.newUser).subscribe({
      next: (response: any) => {
        if (response.status == 'OK') {
          alert(response.message);
          this.router.navigate(['/login']);
        }
      },
      error: (error: any) => {
        error = error.error;
        alert(error.message);
      }
    });
  }
}