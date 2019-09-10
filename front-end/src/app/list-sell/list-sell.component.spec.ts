import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSellComponent } from './list-sell.component';

describe('ListSellComponent', () => {
  let component: ListSellComponent;
  let fixture: ComponentFixture<ListSellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListSellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListSellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
