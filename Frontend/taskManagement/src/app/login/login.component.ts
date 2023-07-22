import { Component } from '@angular/core';
import { LoginData } from '../Model/LoginData';
import { ApiService } from '../Service/api.service';
import { AuthService } from '../Service/AuthService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginData: LoginData = {
    username: '',
    password: ''
  };
  constructor(private apiService: ApiService, private authService: AuthService) { }

  onLoginSubmit() {
    this.apiService.login(this.loginData.username, this.loginData.password)
      .subscribe(
        response => {
          const token=response.jwt;
          this.authService.storeToken(token);
        },
        error => {
          // Handle login error
          console.error(error);
        }
      );
}
}
