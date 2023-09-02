package com.citel.doacao.domain.repository;

import com.citel.doacao.domain.entity.DoadorEntity;
import com.citel.doacao.dto.ImcMedioDTO;
import com.citel.doacao.dto.MediaIdadePorTipoSanguineoDTO;
import com.citel.doacao.dto.PessoasObesasDTO;
import com.citel.doacao.dto.TipoSanguineoEQuantidadeDeReceptores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DoadorRepository extends JpaRepository<DoadorEntity, Long> {

    @Query("SELECT NEW  com.citel.doacao.dto.ImcMedioDTO(" +
            "    FLOOR((YEAR(CURRENT_DATE) - YEAR(d.dataNasc)) / 10) * 10 AS faixaIdade, " +
            "    AVG(d.peso / (d.altura * d.altura)) AS mediaImc ) " +
            "FROM " +
            "  com.citel.doacao.domain.entity.DoadorEntity d " +
            "GROUP BY " +
            "    FLOOR((YEAR(CURRENT_DATE) - YEAR(d.dataNasc)) / 10) * 10 " +
            "ORDER BY " +
            "    faixaIdade")
    Optional<List<ImcMedioDTO>> calcularImcMedioEmCadaFaixaEtariaDeDezEmDezAnos();

    @Query("SELECT NEW com.citel.doacao.dto.PessoasObesasDTO( " +
            "    d.sexo AS sexo, " +
            "    CAST(SUM(CASE WHEN d.peso / (d.altura * d.altura) > 30 THEN 1 ELSE 0 END) AS Integer) AS totalObesos, " +
            "    CAST(COUNT(d) AS Integer) AS qtdDoadores " +
            ") " +
            "FROM com.citel.doacao.domain.entity.DoadorEntity d " +
            "GROUP BY d.sexo ")
    Optional<List<PessoasObesasDTO>> buscarQuantidadePessoasObesosAgrupadasPorGenero();


    @Query("SELECT NEW com.citel.doacao.dto.MediaIdadePorTipoSanguineoDTO(d.tipoSanguineo as tipoSanguineo, " +
            " AVG(EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM d.dataNasc)) AS mediaIdade)" +
            "FROM com.citel.doacao.domain.entity.DoadorEntity d " +
            "GROUP BY d.tipoSanguineo")
    Optional<List<MediaIdadePorTipoSanguineoDTO>> mediaPorTipoSanguineo();


    @Query("SELECT NEW com.citel.doacao.dto.TipoSanguineoEQuantidadeDeReceptores( d.tipoSanguineo AS tipoSanguineo, COUNT(d.id) AS asqtdPossiveisDoadores )" +
            " FROM com.citel.doacao.domain.entity.DoadorEntity d " +
            " WHERE YEAR(CURRENT_DATE) - YEAR(d.dataNasc) BETWEEN 16 AND 69" +
            " AND d.peso > 50" +
            " GROUP BY d.tipoSanguineo")
    Optional<List<TipoSanguineoEQuantidadeDeReceptores>> contarPessoasPorTipoSanguineo();


}