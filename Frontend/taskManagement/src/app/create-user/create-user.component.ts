import { Component } from '@angular/core';
import { ApiService } from '../Service/api.service';
import {CreateUser} from '../Model/CreateUser';
@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent {
    userData: CreateUser = {
      username: '',
      email: '',
      password: '',
      role: ''
    };
  
    constructor(private apiService: ApiService) { }
  
    onCreateUserSubmit() {
      this.apiService.createUser(this.userData.username, this.userData.email, this.userData.password, this.userData.role)
        .subscribe(
          response => {
            // Handle successful user creation response
            console.log(response);
          },
          error => {
            // Handle user creation error
            console.error(error);
          }
        );
    }
}
