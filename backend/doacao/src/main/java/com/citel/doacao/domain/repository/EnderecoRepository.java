package com.citel.doacao.domain.repository;

import com.citel.doacao.domain.entity.EnderecoEntity;
import com.citel.doacao.dto.DoadoresPorEstadoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {
//    List<Object> countByidGroupByEstado(); //select count(e.id), e.estado from tb_endereco e group by e.estado

    // TODO -  a palavra-chave GroupBy não é suportada diretamente nos Query Methods do Spring Data JPA

    //    @Query(value = "SELECT COUNT(e.id), e.estado FROM Endereco e GROUP BY e.estado", nativeQuery = true)
//    @Query(value = "SELECT new com.citel.doacao.domain.model.DoadoresPorEstadoDTO(COUNT(e.id) as qtdDoadores, e.estado as estado) FROM EnderecoEntity e GROUP BY e.estado")
    @Query("select NEW com.citel.doacao.dto.DoadoresPorEstadoDTO(CAST(COUNT(e.id) AS Integer), e.estado) from EnderecoEntity e group by e.estado")
    List<DoadoresPorEstadoDTO> qtdPossiveisDoadoresPorEstado();
}
