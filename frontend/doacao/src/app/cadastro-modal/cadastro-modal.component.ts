import { Component, Inject } from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import { DoadoresPorEstado } from '../interface/response/DoadoresPorEstado';
import { MediaImc } from '../interface/response/MediaImc';
import { PercentualDoadoresObesos } from '../interface/response/PercentualDoadoresObesos';
import { MediaIdadePorTiPoSanguineo } from '../interface/response/MediaIdadePorTipoSanguineo';
import { QuantidadeReceptoresPorTipoSanguineo } from '../interface/response/QuantidadeReceptoresPorTipoSanguineo';
import { DoadorService } from '../service/doador.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DadosEnviarModal } from '../interface/DadosEnviarModal';

@Component({
  selector: 'app-cadastro-modal',
  templateUrl: './cadastro-modal.component.html',
  styleUrls: ['./cadastro-modal.component.css'],
})
export class CadastroModalComponent {
  constructor(
    private doadorService: DoadorService,
    private snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<CadastroModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: DadosEnviarModal,
  ) {}

  filtroDoadoresPorEstado: DoadoresPorEstado[] = [];
  filtroMediaImc: MediaImc[] = [];
  filtroMediaIdadePorTiPoSanguineo: MediaIdadePorTiPoSanguineo[] = [];
  filtroPercentualDoadoresObesos: PercentualDoadoresObesos[] = [];
  filtroQuantidadeReceptoresPorTipoSanguineo: QuantidadeReceptoresPorTipoSanguineo[] = [];

  arquivo:File | null | undefined 
  tipoMetodo = this.data.tipoMetodo;
  listaColunasTabela: string[] = this.data.listaColunasTabela
  arquivoValido = false;

  ngOnInit() {
    this.filtroDoadoresPorEstado = this.data.resDoadoresPorEstado;
    this.filtroMediaImc  = this.data.resMediaImc;
    this.filtroMediaIdadePorTiPoSanguineo = this.data.resMediaIdadePorTipoSanguineo;
    this.filtroPercentualDoadoresObesos = this.data.resPercentualDoadoresObesos;
    this.filtroQuantidadeReceptoresPorTipoSanguineo = this.data.resQuantidadeReceptoresPorTipoSanguineo;
  }
  cadastrarPossiveisDoadores(){

      if(this.arquivo != null && this.arquivoValido){
        this.doadorService.postDoadores(this.arquivo).subscribe(
          res=>{
            this.mostrarToast("Arquivo salvo com sucesso!")
            this.fecharModal();
          }
          ,error=> {
          this.mostrarToast("Ocorreu um erro!");
        });        
      }
      else {
        this.mostrarToast("Arquivo Inv!");
      }
  
  }
  atribuirArquivo(event: Event){
    let target = event.target as HTMLInputElement;
    
    if(target.files != null && target.files.length == 1 && target.files[0].type == "application/json"){
      this.arquivo  = target.files[0];
      this.arquivoValido = true;
      console.log("certo")
    }
    else{
      this.arquivoValido = false;
      console.log("errado")
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
  arredondaParaBaixo(valor: number): number {
    return Math.floor(valor);
  }
  fecharModal(){
    this.limpar();
    this.dialogRef.close();
  }
  private limpar(){
    this.data.resDoadoresPorEstado = [];
    this.data.resMediaImc  = [];
    this.data.resMediaIdadePorTipoSanguineo = [];
    this.data.resPercentualDoadoresObesos = [];
    this.data.resQuantidadeReceptoresPorTipoSanguineo = [];
    this.data.listaColunasTabela = []
    this.data.tipoMetodo = ""
  }
}
