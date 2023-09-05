import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { DoadoresPorEstado } from 'src/app/interface/response/DoadoresPorEstado';
import { MediaImc } from 'src/app/interface/response/MediaImc';
import { PercentualDoadoresObesos } from 'src/app/interface/response/PercentualDoadoresObesos';
import { MediaIdadePorTiPoSanguineo } from 'src/app/interface/response/MediaIdadePorTipoSanguineo';
import { QuantidadeReceptoresPorTipoSanguineo } from 'src/app/interface/response/QuantidadeReceptoresPorTipoSanguineo';
import { Observable } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class DoadorService {

  constructor(private http: HttpClient) {}

  private apiUrl = 'http://localhost:8080/doadores/';
  
  postDoadores(arquivo: File):Observable<Object> {
      const formData = new FormData();
      formData.append('file', arquivo);
      return this.http.post(this.apiUrl, formData);
  }
  
  getDadosDaAPIDoadoresPorEstado(): Observable<DoadoresPorEstado[]> {
    return this.http.get<DoadoresPorEstado[]>(this.apiUrl+"buscar-por-estado")
  }

  getDadosDaAPICalcularMediaImc(): Observable<MediaImc[]> {
    return this.http.get<MediaImc[]>(this.apiUrl+"calcular-media-imc");
  }

  getDadosDaAPIPercentualDoadoresObesos(): Observable<PercentualDoadoresObesos[]> {
    return this.http.get<PercentualDoadoresObesos[]>(this.apiUrl+"percentual-pessoas-obesas");
  }

  getDadosDaAPIMediaIdadePorTiPoSanguineo(): Observable<MediaIdadePorTiPoSanguineo[]> {
    return this.http.get<MediaIdadePorTiPoSanguineo[]>(this.apiUrl+"media-por-tipo-sanguineo");
  }

  getDadosDaAPIQtdPossiveisDoadoresPorTipoSanguineo(): Observable<QuantidadeReceptoresPorTipoSanguineo[]> {
    return this.http.get<QuantidadeReceptoresPorTipoSanguineo[]>(this.apiUrl+"quantidade-receptores-por-tipo-sanguineo");
}


}