import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { DoadoresPorEstado } from '../interface/DoadoresPorEstado';
import { Observable } from 'rxjs';
import { MediaImc } from '../interface/MediaImc';
import { PercentualDoadoresObesos } from '../interface/PercentualDoadoresObesos';
import { MediaIdadePorTiPoSanguineo } from '../interface/MediaIdadePorTiPoSanguineo';
import { QuantidadeReceptoresPorTipoSanguineo } from '../interface/QuantidadeReceptoresPorTipoSanguineo';
import { DoadorService } from '../service/doador.service';

@Component({
  selector: 'app-cadastro-modal',
  templateUrl: './cadastro-modal.component.html',
  styleUrls: ['./cadastro-modal.component.css'],
})
export class CadastroModalComponent {
  constructor(private doadorService: DoadorService,
    public dialogRef: MatDialogRef<CadastroModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: number,
  ) {}
  
  
  filtroDoadoresPorEstado: DoadoresPorEstado[] = [];
  filtroMediaImc: MediaImc[] = [];
  filtroPercentualDoadoresObesos: PercentualDoadoresObesos[] = [];
  filtroMediaIdadePorTiPoSanguineo: MediaIdadePorTiPoSanguineo[] = [];
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
        this.calcularMediaImc();
        break
      case 4:
        this.percentualPercentualDoadoresObeso();
        break
      case 5:
        this.mediaIdadePorTiPoSanguineo();
        break
      case 6:
          this.qtdPossiveisDoadoresPorTipoSanguineo();
          break
    }

  }
  cadastrarPossiveisDoadores(){
    if(this.arquivo != null && this.arquivoValido){
      this.doadorService.postDoadores(this.arquivo).subscribe(); 
     console.log("Aee")
    //  this.fechar();
    }
    else{
      alert("Arqiovo Inválido")
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
    this.doadorService.getDadosDaAPIDoadoresPorEstado().subscribe(res =>{
      this.filtroDoadoresPorEstado = res;
    });;
  }
  calcularMediaImc() : void{
    this.doadorService.getDadosDaAPICalcularMediaImc().subscribe(res =>{
      this.filtroMediaImc = res;
    });;
  }
  percentualPercentualDoadoresObeso() :void{
    this.doadorService.getDadosDaAPIPercentualDoadoresObesos().subscribe(res =>{
      this.filtroPercentualDoadoresObesos = res;
    });;
  }
  mediaIdadePorTiPoSanguineo() : void{
    this.doadorService.getDadosDaAPIMediaIdadePorTiPoSanguineo().subscribe(res =>{
      this.filtroMediaIdadePorTiPoSanguineo = res;
    });;
  }
  qtdPossiveisDoadoresPorTipoSanguineo() : void{
    this.doadorService.getDadosDaAPIQtdPossiveisDoadoresPorTipoSanguineo().subscribe(res =>{
      this.filtroQuantidadeReceptoresPorTipoSanguineo = res;
    });;
  }
  fechar(){
    this.dialogRef.close()
  }
}
