import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AdminloginComponent } from './page/adminlogin/adminlogin.component';
import { LoginComponent } from './page/login/login.component';
import { Error404Component } from './page/error/error404/error404.component';
import { AccueilComponent } from './page/accueil/accueil.component';
import { AccueilAdminComponent } from './page/accueil-admin/accueil-admin.component';
import { VilleinsertComponent } from './page/ville/villeinsert/villeinsert.component';
import { RoutesInsertComponent } from './page/routes/routes-insert/routes-insert.component';
import { EtatInsertComponent } from './page/routes/etat/etat-insert/etat-insert.component';
import { PortionsInsertComponent } from './page/routes/portions/portions-insert/portions-insert.component';
import { RoutesListeComponent } from './page/routes/routes-liste/routes-liste.component';

@NgModule({
  declarations: [
    AppComponent,
    AdminloginComponent,
    LoginComponent,
    Error404Component,
    AccueilComponent,
    AccueilAdminComponent,
    VilleinsertComponent,
    RoutesInsertComponent,
    EtatInsertComponent,
    PortionsInsertComponent,
    RoutesListeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
