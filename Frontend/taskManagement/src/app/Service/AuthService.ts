import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly TOKEN_KEY = 'jwt_token';
  private readonly time=Date.now().toString();

  storeToken(token: string): void {
    sessionStorage.setItem(this.TOKEN_KEY, token);
    sessionStorage.setItem("TIME", this.time);
  }

  getToken(): string | null {
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  clearToken(): void {
    sessionStorage.removeItem(this.TOKEN_KEY);
  }
}
