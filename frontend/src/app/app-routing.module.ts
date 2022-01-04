import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DatabaseComponent} from './components/database/database.component';
import { HomeComponent} from './components/home/home.component';
import { InfoComponent } from './components/info/info.component';
import { UpdateComponent } from './components/update/update.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'database', component: DatabaseComponent},
  { path: 'info', component: InfoComponent},
  { path: 'update/brand/:name', component: UpdateComponent},
  { path: 'update/model/:id', component: UpdateComponent},
  { path: 'update/car/:vin', component: UpdateComponent},
  { path: 'update/customer/:id', component: UpdateComponent},
  { path: 'update/details/:name', component: UpdateComponent},
  { path: 'update/sale/:id', component: UpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [DatabaseComponent, HomeComponent, UpdateComponent, InfoComponent];
