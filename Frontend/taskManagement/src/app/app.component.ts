import { Component } from '@angular/core';
import { AuthService } from './Service/AuthService';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'taskManagement';
  isValid=false;
  constructor(private authService: AuthService){
    const token=this.authService.getToken();
    console.log(token);
    if(token!=null){
      this.isValid=true;
    }
  }
}
