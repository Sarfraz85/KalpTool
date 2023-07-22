import { Component } from '@angular/core';
import { Client } from '../Model/Client';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.css']
})
export class CreateClientComponent {
  clientData: Client = new Client('', ''); 

  constructor(private apiService: ApiService){}
  onCreateClientSubmit() {
    this.apiService.createClient(this.clientData).subscribe(
      response => {
        alert(response);
        console.log(response);
      },
      error => {
        // Handle login error
        console.error(error);
      }
    );
    console.log('Client Data:', this.clientData);
  }
}
