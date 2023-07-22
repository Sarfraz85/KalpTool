import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Client } from '../Model/Client';
import { Task } from '../Model/Task';
import { Subtask } from '../Model/SubTask';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const loginData = { username, password };
    console.log(loginData)
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'});
  let options = { headers: headers };
    return this.http.post<any>(`${this.apiUrl}/gettoken/login`, loginData,options);
  }

  createUser(username: string, email: string, password: string, role: string) {
    const userData = { username, email, password, role };
    console.log(userData);
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'});
  let options = { headers: headers };
    return this.http.post(`${this.apiUrl}/gettoken/register`, userData,options);
  }

  createClient(client: Client){
    return this.http.post<any>(`${this.apiUrl}/create-client`, client);
  }

  createTask(task: Map<Task, Subtask[]>){
    console.log(task);
    return this.http.post<any>(`${this.apiUrl}/create-task`, task);
  }
}
