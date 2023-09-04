import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { CadastroModalComponent } from './cadastro-modal/cadastro-modal.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
  
})
export class AppComponent {
  opcao_selecionada = 0;

  constructor(public dialog: MatDialog) { 
  }
  title = 'Bem Vindo ao site Doações.com';
  ngOnInit() {
    this.opcao_selecionada = 1
  }
  abrirModal() {
    this.dialog.open(CadastroModalComponent, {
      data: this.opcao_selecionada,
    });
  }
}
