import { Component, OnInit, ViewChild } from '@angular/core';
import { Sell } from '../model/SellBO';
import {SelectionModel} from '@angular/cdk/collections';
import { SellService } from '../service/SellService';
import { Router } from '@angular/router';
import { MatTable, MatTableDataSource, Sort, MatPaginator, PageEvent } from '@angular/material';
import { SellPaginator } from '../model/SellPaginator';

@Component({
  selector: 'app-list-sell',
  templateUrl: './list-sell.component.html',
  styleUrls: ['./list-sell.component.css'],
  providers: [SellService]
})
export class ListSellComponent implements OnInit {

  sells: SellPaginator;
  displayedColumns: string[] = ['id', 'name', 'value', 'user', 'actions'];
  dataSource: MatTableDataSource<Sell>;
  selection = new SelectionModel<Sell>(true, []);
  pageEvent: PageEvent;
  pageIndex:number;
  pageSize:number;
  length:number;
  @ViewChild(MatTable, {static: false}) table: MatTable<any>;
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  constructor(
    private router: Router,
    private SellService: SellService
  ) { }

  ngOnInit() {
    this.getSells();
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getSells(): void {
    this.SellService.getSells()
      .subscribe(sells => {
        this.sells = sells
        this.dataSource = new MatTableDataSource(sells.content.slice());
        this.pageIndex = sells.number
        this.pageSize = sells.size
        this.length = sells.totalElements
        this.dataSource.paginator = this.paginator;
      });
  }

  deleteElement(element: any) {
    this.SellService.deleteSell(element.id)
  }

  filter(value: string) {
    if(value.length > 0){
      this.SellService.filterSells(value)
        .subscribe(sells => {
          this.sells.content = sells
          this.table.dataSource = sells
          this.table.renderRows()
        });
    } else {
      this.getSells();
    }
  }

  getServerData(event: any) {
    console.log(event)
    this.SellService.getSells(event.pageSize, event.pageIndex)
      .subscribe(sells => {
        this.sells = sells
        this.dataSource = new MatTableDataSource(sells.content.slice());
        this.pageIndex = sells.number
        this.pageSize = sells.size
        this.length = sells.totalElements
      });
  }

  createElement() {
    this.router.navigate(['/create']);
  }

}
