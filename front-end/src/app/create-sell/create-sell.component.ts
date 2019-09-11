import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/UserService';
import { Router } from '@angular/router';
import { User } from '../model/UserBO';
import { FormGroup, FormControl } from '@angular/forms';
import { SellService } from '../service/SellService';
import { Sell } from '../model/SellBO';

@Component({
  selector: 'app-create-sell',
  templateUrl: './create-sell.component.html',
  styleUrls: ['./create-sell.component.css'],
  providers: [UserService, SellService]
})
export class CreateSellComponent implements OnInit {

  sellForm = new FormGroup({
    name: new FormControl(''),
    value: new FormControl(''),
    userBO: new FormGroup({
      id: new FormControl()
    })
  });

  users: User[];
  constructor(
    private router: Router,
    private UserService: UserService,
    private SellService: SellService
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

  onSubmit(){
    var sell: Sell = {
      name: this.sellForm.value.name,
      value: this.sellForm.value.value,
      userBO: {
        id: this.sellForm.value.userBO.id,
      },
    }
    console.log(this.sellForm.value)
    this.SellService.createSell(sell).subscribe(sell => {
      this.router.navigate(['/list']);
    });
  }

}
