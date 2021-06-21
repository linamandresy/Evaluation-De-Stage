import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilAdminComponent } from './page/accueil-admin/accueil-admin.component';
import { AccueilComponent } from './page/accueil/accueil.component';
import { AdminloginComponent } from './page/adminlogin/adminlogin.component';
import { Error404Component } from './page/error/error404/error404.component';
import { LoginComponent } from './page/login/login.component';
import { EtatInsertComponent } from './page/routes/etat/etat-insert/etat-insert.component';
import { PortionsInsertComponent } from './page/routes/portions/portions-insert/portions-insert.component';
import { RoutesInsertComponent } from './page/routes/routes-insert/routes-insert.component';
import { RoutesListeComponent } from './page/routes/routes-liste/routes-liste.component';
import { VilleinsertComponent } from './page/ville/villeinsert/villeinsert.component';

const routes: Routes = [{
  path:'admin/login',
  component:AdminloginComponent
},{
  path:'login',
  component:LoginComponent
},{
  path:'error/:message',
  component:Error404Component
},{
  path:'',
  component:AccueilComponent
},{
  path:'admin/login',
  component:AdminloginComponent
},{
  path:'admin',
  component:AccueilAdminComponent
},{
  path:'ville/insert',
  component:VilleinsertComponent
},{
  path:'routes',
  component:RoutesListeComponent
},{
  path:'routes/insert',
  component:RoutesInsertComponent
},{
  path:'etats/insert',
  component:EtatInsertComponent
},{
  path:'routes/:id/portion/insert',
  component:PortionsInsertComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
