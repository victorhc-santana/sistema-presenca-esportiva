package br.edu.fateczl.presencaesportiva.DAO;


import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Turma;

public interface turmaDAO {
    void cadastrar(Turma a);
    void apagar(Turma f);
    void atualizar(int id, Turma f);
    List<Turma> pesquisarPorNome(String nome);
}


