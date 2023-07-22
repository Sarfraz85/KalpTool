import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray, AbstractControl, FormControl } from '@angular/forms';
import { Subtask } from '../Model/SubTask';
import { Task } from '../Model/Task';
import { AuthService } from '../Service/AuthService';
import { ApiService } from '../Service/api.service';

@Component({
  selector: 'app-create-task',
  templateUrl: './create-task.component.html',
  styleUrls: ['./create-task.component.css']
})
export class CreateTaskComponent implements OnInit {
  taskForm!: FormGroup;

  constructor(private fb: FormBuilder,private authService: AuthService,private apiService: ApiService) {}

  ngOnInit() {
    this.taskForm = this.fb.group({
      taskTitle: ['', Validators.required],
      subtasks: this.fb.array([]),
    });
  }

  get subtasks(): FormArray {
    return this.taskForm.get('subtasks') as FormArray;
  }

  addSubtask() {
    this.subtasks.push(this.fb.control('', Validators.required));
  }

  removeSubtask(index: number) {
    this.subtasks.removeAt(index);
  }

  getFormControl(index: number): FormControl {
    return this.subtasks.controls[index] as FormControl;
  }

  onSubmit() {
    if (this.taskForm.valid) {
      const taskTitle = this.taskForm.value.taskTitle;
      const subtasksData = this.taskForm.value.subtasks;
      const subtasks = subtasksData.map((title: string) => new Subtask(title));

      const task = new Task(taskTitle);
      const taskSubtasks = new Map<Task, Subtask[]>();
      taskSubtasks.set(task, subtasks);

      // Do whatever you want with the task and subtasks data, e.g., save to the server
      this.apiService.createTask(taskSubtasks)
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
      console.log('Task:', task);
      console.log('Subtasks:', subtasks);
    }
  }
}
