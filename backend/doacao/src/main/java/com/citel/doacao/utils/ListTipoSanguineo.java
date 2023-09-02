package com.citel.doacao.utils;

import com.citel.doacao.dto.TabelaTipoSanguineo;

import java.util.*;

public class ListTipoSanguineo {
    public static List<TabelaTipoSanguineo> tipoSanguineoEseusReceptores(){

        List<TabelaTipoSanguineo>listTabelaTipoSanguineos = new ArrayList<>();
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("A+").tiposReceptores(new ArrayList<>(Arrays.asList("A+", "A-", "O+", "O-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("A-").tiposReceptores(new ArrayList<>(Arrays.asList("A-","O-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("B+").tiposReceptores(new ArrayList<>(Arrays.asList("B+", "B-", "O+", "O-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("B-").tiposReceptores(new ArrayList<>(Arrays.asList("B-","O-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("AB+").tiposReceptores(new ArrayList<>(Arrays.asList("A+", "B+","O+","AB+","A-","B-","O-","AB-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("AB-").tiposReceptores(new ArrayList<>(Arrays.asList("A-", "B-", "O-", "AB-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add(TabelaTipoSanguineo.builder().tipoSanguineo("O+").tiposReceptores(new ArrayList<>(Arrays.asList("O+", "O-"))).tiposDoadores(new ArrayList<>()).build());
        listTabelaTipoSanguineos.add( TabelaTipoSanguineo.builder().tipoSanguineo("O-").tiposReceptores(new ArrayList<>(Collections.singletonList("O-"))).tiposDoadores(new ArrayList<>()).build());
        return listTabelaTipoSanguineos;
    }
}
