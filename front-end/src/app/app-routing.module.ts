import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditSellComponent } from './edit-sell/edit-sell.component';
import { ListSellComponent } from './list-sell/list-sell.component';
import { CreateSellComponent } from './create-sell/create-sell.component';

const routes: Routes = [
  { path: 'list', component: ListSellComponent },
  { path: 'edit/:id', component: EditSellComponent },
  { path: 'create', component: CreateSellComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
