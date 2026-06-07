package br.edu.fateczl.presencaesportiva.DAO;

import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Aluno;

public interface alunoDAO {
    void cadastrar(Aluno a);
    void apagar(Aluno f);
    void atualizar(long id, Aluno f);
    List<Aluno> pesquisarPorNome(String nome);
}

