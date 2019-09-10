import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/UserService';
import { Router } from '@angular/router';
import { User } from '../model/UserBO';

@Component({
  selector: 'app-create-sell',
  templateUrl: './create-sell.component.html',
  styleUrls: ['./create-sell.component.css'],
  providers: [UserService]
})
export class CreateSellComponent implements OnInit {

  users: User[];
  constructor(
    private router: Router,
    private UserService: UserService
  ) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.UserService.getUsers()
      .subscribe(users => {
        this.users = users
      });
  }

}
