import { DoadoresPorEstado } from "./response/DoadoresPorEstado";
import { MediaIdadePorTiPoSanguineo } from "./response/MediaIdadePorTipoSanguineo";
import { MediaImc } from "./response/MediaImc";
import { PercentualDoadoresObesos } from "./response/PercentualDoadoresObesos";
import { QuantidadeReceptoresPorTipoSanguineo } from "./response/QuantidadeReceptoresPorTipoSanguineo";

export interface DadosEnviarModal{
    listaColunasTabela: string[];
    tipoMetodo: string;
    resDoadoresPorEstado: DoadoresPorEstado[]
    resMediaIdadePorTipoSanguineo: MediaIdadePorTiPoSanguineo[]
    resMediaImc: MediaImc[]
    resPercentualDoadoresObesos: PercentualDoadoresObesos[]
    resQuantidadeReceptoresPorTipoSanguineo: QuantidadeReceptoresPorTipoSanguineo[]
}