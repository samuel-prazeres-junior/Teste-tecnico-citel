import { Component } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import { CadastroModalComponent } from './cadastro-modal/cadastro-modal.component';
import { DoadorService } from './service/doador.service';
import { DadosEnviarModal } from './interface/DadosEnviarModal';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  opcao_selecionada = 1;
  dadosInicializarModal:DadosEnviarModal = {
    listaColunasTabela: [],
    tipoMetodo: '',
    resDoadoresPorEstado: [],
    resMediaIdadePorTipoSanguineo: [],
    resMediaImc: [],
    resPercentualDoadoresObesos: [],
    resQuantidadeReceptoresPorTipoSanguineo: []
  }

  constructor(public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private doadorService: DoadorService) { 
  }

  title = 'Bem Vindo ao site Doações.com';
  
  ngOnInit() {
    this.opcao_selecionada = 1
  }

  abrirModal(dados: DadosEnviarModal ) {
    this.dialog.open(CadastroModalComponent, {
      data: dados,
      disableClose: true
    });
  }

  verificarSelecao(){
    switch(this.opcao_selecionada){
      case 1:
        this.dadosInicializarModal.tipoMetodo = 'POST'
        this.abrirModal(this.dadosInicializarModal);
        break
      case 2:
        this.dadosInicializarModal.tipoMetodo = 'GET'
        this.dadosInicializarModal.listaColunasTabela = ["Estado", "Quantidade"]
        this.doadorService.getDadosDaAPIDoadoresPorEstado()
        .subscribe(res=>{
          this.dadosInicializarModal.resDoadoresPorEstado = res;
       
          this.abrirModal(this.dadosInicializarModal);
        }, erro=> this.mostrarToast("Ocorreu um erro!"));
        break
      case 3:
        this.dadosInicializarModal.tipoMetodo = 'GET'
        this.dadosInicializarModal.listaColunasTabela = ["Tipo Sanguineo", "Media de Idade"]
        this.doadorService.getDadosDaAPIMediaIdadePorTiPoSanguineo()
        .subscribe(res=>{
          this.dadosInicializarModal.resMediaIdadePorTipoSanguineo = res;
          this.abrirModal(this.dadosInicializarModal);
        }, erro => this.mostrarToast("Ocorreu um erro!"));
        break
      case 4:
        this.dadosInicializarModal.tipoMetodo = 'GET'
        this.dadosInicializarModal.listaColunasTabela = ["Faixa de idade", "Media de IMC"]
        this.doadorService.getDadosDaAPICalcularMediaImc()
        .subscribe(res=>{
          this.dadosInicializarModal.resMediaImc = res;
          this.abrirModal(this.dadosInicializarModal);
        }, erro => this.mostrarToast("Ocorreu um erro!"));
        break
      case 5:
        this.dadosInicializarModal.tipoMetodo = 'GET'  
        this.dadosInicializarModal.listaColunasTabela = ["Sexo", "Quantidade de Obesos", "Quantidade de doadores","Porcentagem de obesos"]
        this.doadorService.getDadosDaAPIPercentualDoadoresObesos()
        .subscribe(res=>{
          this.dadosInicializarModal.resPercentualDoadoresObesos = res;
          this.abrirModal(this.dadosInicializarModal);
        }, erro => this.mostrarToast("Ocorreu um erro!"));
        break
      case 6:
        this.dadosInicializarModal.tipoMetodo = 'GET'
        this.dadosInicializarModal.listaColunasTabela = ["Tipo Sanguineo", "Quantidade Doadores"]
        this.doadorService.getDadosDaAPIQtdPossiveisDoadoresPorTipoSanguineo()
        .subscribe(res=>{
          this.dadosInicializarModal.resQuantidadeReceptoresPorTipoSanguineo = res;
          this.abrirModal(this.dadosInicializarModal);
        }, erro => this.mostrarToast("Ocorreu um erro!"));
        break
    }
  }
  mostrarToast(mensgem: string) {
    this.snackBar.open(mensgem, 'Fechar', {
      duration: 5000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: ['toast'],
    });
  }
}
