import { Component } from '@angular/core';
import{DoadorService} from './doador/service/doador.service'
import { DoadoresPorEstado } from './interface/DoadoresPorEstado';
import { MediaImc } from './interface/MediaImc';
import { PercentualDoadoresObesos } from './interface/PercentualDoadoresObesos';
import { MediaIdadePorTiPoSanguineo } from './interface/MediaIdadePorTiPoSanguineo';
import { QuantidadeReceptoresPorTipoSanguineo } from './interface/QuantidadeReceptoresPorTipoSanguineo';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  response1: DoadoresPorEstado[] = [];
  response2: MediaImc[] = [];
  response3: PercentualDoadoresObesos[] = [];
  response4: MediaIdadePorTiPoSanguineo[] = [];
  response5: QuantidadeReceptoresPorTipoSanguineo[] = [];

  constructor(private doador: DoadorService) { }
  title = 'Bem Vindo ao site Doações.com';

  ngOnInit() {
    this.doador.getDadosDaAPIDoadoresPorEstado().subscribe((dados :DoadoresPorEstado[]) => {
      this.response1 = dados
    });

    this.doador.getDadosDaAPICalcularMediaImc().subscribe((dados :MediaImc[]) => {
      this.response2 = dados
      console.log(dados)
    });

    this.doador.getDadosDaAPIPercentualDoadoresObesos().subscribe((dados :PercentualDoadoresObesos[]) => {
      this.response3 = dados
    });

    this.doador.getDadosDaAPIMediaIdadePorTiPoSanguineo().subscribe((dados :MediaIdadePorTiPoSanguineo[]) => {
      this.response4 = dados
    });

    this.doador.getDadosDaAPIQtdPossiveisDoadoresPorTipoSanguineo().subscribe((dados :QuantidadeReceptoresPorTipoSanguineo[]) => {
      this.response5 = dados
    });

  }
}