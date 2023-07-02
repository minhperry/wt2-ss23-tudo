import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  luminary_icon = 'assets/images/luminary_icon.png';

  constructor(
    private cookie: CookieService,
    private router: Router
  ) { }

  userIsLoggedIn(): boolean {
    var cookie = this.cookie.get('accessToken');
    return cookie != '';
  }

  onLogoutClick() {
    this.cookie.deleteAll();
    this.router.navigate(['/']);
  }
}
