package br.edu.fateczl.presencaesportiva.DAO;

import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Presenca;

public interface PresencaDAO {
    void cadastrar(Presenca p);
    void apagar(Presenca p);
    void atualizar(long id, Presenca p);
    Presenca buscarPorMatriculaEDia(long matriculaId, LocalDate dia);
    List<Presenca> pesquisarPorDia(String nomeTurma, LocalDate dia);
}
