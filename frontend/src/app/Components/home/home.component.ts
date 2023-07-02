import { Component } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(private cookie: CookieService){}

  logoUrl = 'assets/images/luminary_logo.png';
  arrowUrl = 'assets/images/arrow_down.png';

  userIsLoggedIn(): boolean {
    var cookie = this.cookie.get('accessToken');
    return cookie != '';
  }
}
