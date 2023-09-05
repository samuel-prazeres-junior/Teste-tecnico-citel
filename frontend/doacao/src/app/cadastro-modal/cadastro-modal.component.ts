import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { DoadoresPorEstado } from '../interface/DoadoresPorEstado';
import { Observable } from 'rxjs';
import { MediaImc } from '../interface/MediaImc';
import { PercentualDoadoresObesos } from '../interface/PercentualDoadoresObesos';
import { MediaIdadePorTiPoSanguineo } from '../interface/MediaIdadePorTiPoSanguineo';
import { QuantidadeReceptoresPorTipoSanguineo } from '../interface/QuantidadeReceptoresPorTipoSanguineo';
import { DoadorService } from '../service/doador.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-cadastro-modal',
  templateUrl: './cadastro-modal.component.html',
  styleUrls: ['./cadastro-modal.component.css'],
})
export class CadastroModalComponent {
  constructor(private doadorService: DoadorService,
    private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<CadastroModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: number,
  ) {}
  
  filtroDoadoresPorEstado: DoadoresPorEstado[] = [];
  filtroMediaImc: MediaImc[] = [];
  filtroMediaIdadePorTiPoSanguineo: MediaIdadePorTiPoSanguineo[] = [];
  filtroPercentualDoadoresObesos: PercentualDoadoresObesos[] = [];
  filtroQuantidadeReceptoresPorTipoSanguineo: QuantidadeReceptoresPorTipoSanguineo[] = [];
  arquivo:File | null | undefined 
  opcao_selecionada = this.data;
  arquivoValido = false;

  ngOnInit() {
    switch(this.opcao_selecionada){
      case 2:
        this.qtdDoadoresPorEstado();
        break
      case 3:
        this.mediaIdadePorTiPoSanguineo();
        break
      case 4:
        this.calcularMediaImc();
        break
      case 5:
        this.percentualPercentualDoadoresObeso();
        break
      case 6:
          this.qtdPossiveisDoadoresPorTipoSanguineo();
          break
    }

  }
  cadastrarPossiveisDoadores(){
    if(this.arquivo != null && this.arquivoValido){
      this.doadorService.postDoadores(this.arquivo).subscribe(); 
      this.mostrarToast("Arquivo salvo com sucesso!")
     this.fechar();
    }
    else{
      this.mostrarToast("Ocorreu um erro!");
    }
  }
  atribuirArquivo(event: Event){
    let target = event.target as HTMLInputElement;
    
    if(target.files != null && target.files.length == 1 && target.files[0].type == "application/json"){
      this.arquivo  = target.files[0];
      this.arquivoValido = true;
    }
    else{
      this.arquivoValido = false;
    }
  }
  qtdDoadoresPorEstado() :void{
    this.doadorService.getDadosDaAPIDoadoresPorEstado().subscribe(
      (value) => this.filtroDoadoresPorEstado = value,
      (error) =>{
        this.mostrarToast("Ocorreu um erro!")
        this.fechar();
      } 
      
    );
  }
  mediaIdadePorTiPoSanguineo() : void{
    this.doadorService.getDadosDaAPIMediaIdadePorTiPoSanguineo().subscribe(
      (value) => this.filtroMediaIdadePorTiPoSanguineo = value,
      (error) => {
        this.mostrarToast("Ocorreu um erro!") ;
        this.fechar();
      }
    );
  }
  calcularMediaImc() : void{
    this.doadorService.getDadosDaAPICalcularMediaImc().subscribe(
      (value) => this.filtroMediaImc = value,
      (error) => {
        this.mostrarToast("Ocorreu um erro!");
        this.fechar();
      }
    );
  }
  percentualPercentualDoadoresObeso() :void{
    this.doadorService.getDadosDaAPIPercentualDoadoresObesos().subscribe(
      (value) => this.filtroPercentualDoadoresObesos = value,
      (error) => {
        this.mostrarToast("Ocorreu um erro!");
        this.fechar();
      }
    );
  }
  qtdPossiveisDoadoresPorTipoSanguineo() : void{
    this.doadorService.getDadosDaAPIQtdPossiveisDoadoresPorTipoSanguineo().subscribe(
      (value) => this.filtroQuantidadeReceptoresPorTipoSanguineo = value,
      (error) => {
        this.mostrarToast("Ocorreu um erro!"); 
        this.fechar();
      }
    );
  }
  mostrarToast(mensgem: string) {
    this.snackBar.open(mensgem, 'Fechar', {
      duration: 5000,
      horizontalPosition: 'end',
      verticalPosition: 'top',
      panelClass: ['erro-toast'],
    });
  }

  fechar(){
    this.dialogRef.close()
  }
}
