package br.edu.fateczl.presencaesportiva.DAO;

import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Matricula;

public interface MatriculaDAO {
    void cadastrar(Matricula a);
    void apagar(Matricula f);
    void atualizar(long id, Matricula f);
    List<Matricula> pesquisarPorAluno (String nomeAluno);
      
}
