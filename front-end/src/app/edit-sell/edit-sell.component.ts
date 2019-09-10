import { Component, OnInit } from '@angular/core';
import { Sell } from '../model/SellBO';
import {SelectionModel} from '@angular/cdk/collections';
import { SellService } from '../service/SellService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-sell',
  templateUrl: './edit-sell.component.html',
  styleUrls: ['./edit-sell.component.css']
})
export class EditSellComponent implements OnInit {

  sells: Sell[];
  displayedColumns: string[] = ['id', 'name', 'value'];
  selection = new SelectionModel<Sell>(true, []);
  constructor(
    private router: Router,
    private SellService: SellService
  ) { }

  ngOnInit() {
    this.getSells();
  }

  getSells(): void {
    this.SellService.getSells()
      .subscribe(sells => this.sells = sells.content);
  }

}
