package com.example.fut_order.repository;

import com.example.fut_order.model.Camisa;
import com.example.fut_order.model.enums.TipoVersao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CamisaRepository extends JpaRepository<Camisa, Long> {

    // O Spring lê esse nome e traduz para:
    // SELECT * FROM camisa WHERE LOWER(time) LIKE LOWER('%' + time + '%')
    List<Camisa> findByTimeContainingIgnoreCase(String time);

    List<Camisa>findByTemporadaContainingIgnoreCase(String temporada);

    @Query("SELECT c FROM Camisa c WHERE LOWER(c.time) LIKE LOWER(CONCAT('%', :time, '%')) AND LOWER(c.temporada) LIKE LOWER(CONCAT('%', :temporada, '%'))")
    List<Camisa> buscarPorTimeETemporada(String time, String temporada);

    // O Spring traduz para: SELECT * FROM camisa WHERE versao = ?
    List<Camisa>findByVersao(TipoVersao versao);


}
