package br.edu.fateczl.presencaesportiva.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.presencaesportiva.DAO.AlunoDAOImplementation;
import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAO;
import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAOImplementation;
import br.edu.fateczl.presencaesportiva.DAO.TurmaDAOImplementation;
import br.edu.fateczl.presencaesportiva.model.Aluno;
import br.edu.fateczl.presencaesportiva.model.Matricula;
import br.edu.fateczl.presencaesportiva.model.Turma;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MatriculaControl {

    private ObservableList<Matricula> matriculas = FXCollections.observableArrayList();
    private MatriculaDAO dao;

    public MatriculaControl() {
        try {
            dao = new MatriculaDAOImplementation();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        carregar();
    }
    private LongProperty id = new SimpleLongProperty(0);
    private ObjectProperty<Aluno> aluno = new SimpleObjectProperty<>(null);
    private ObjectProperty<Turma> turma = new SimpleObjectProperty<>(null);
    private ObjectProperty<LocalDate> dataMatricula = new SimpleObjectProperty<>(LocalDate.now());
    private StringProperty nomeAluno = new SimpleStringProperty("");
    private StringProperty nomeTurma = new SimpleStringProperty(""); 

    public ObservableList<Matricula> getLista() {
        return matriculas;
    }

    public void fromEntity(Matricula matricula) {
        if (matricula != null) {
            id.set(matricula.getId());
            aluno.set(matricula.getAluno());
            turma.set(matricula.getTurma());
            dataMatricula.set(matricula.getDataMatricula());
        }
    }

    public void limparCampos() {
        id.set(0);
        aluno.set(null);
        turma.set(null);
        dataMatricula.set(null);
    }

    public Matricula toEntity() {
        Matricula matricula = new Matricula();
        matricula.setId(id.get());
        matricula.setAluno(aluno.get());
        matricula.setTurma(turma.get());
        matricula.setDataMatricula(dataMatricula.get());
        nomeAluno.set(matricula.getAluno().getNome()); // atualiza a String
        nomeTurma.set(matricula.getTurma().getNome());

        return matricula;

    }

    public void salvar() {
        Matricula matricula = toEntity();
        System.out.println("ID da Matricula ==> " + matricula.getId());
        if (id.get() > 0) {
            dao.atualizar(id.get(), matricula);
        } else {
            dao.cadastrar(matricula);
        }
        limparCampos();
        carregar();

    }

    private void carregar() {
        matriculas.clear();
        matriculas.addAll(dao.pesquisarPorAluno("")); // string vazia retorna todos pelo LIKE %%
    }

    public void pesquisar(String nomeAluno) {
        matriculas.clear();
        matriculas.addAll(dao.pesquisarPorAluno(nomeAluno));
    }   

    public void excluir(int indice) throws SQLException {
        Matricula a = matriculas.get(indice);
        dao.apagar(a);
        carregar();
    }

    public void salvarPorNome(String nomeAluno, String nomeTurma, LocalDate data) throws Exception {
        // busca aluno pelo nome
        AlunoDAOImplementation alunoDAO = new AlunoDAOImplementation();
        List<Aluno> lista = alunoDAO.pesquisarPorNome(nomeAluno);
        Aluno a = null;
        if (!lista.isEmpty()) {
            a = lista.get(0);
        }

        // busca turma pelo nome
        TurmaDAOImplementation turmaDAO = new TurmaDAOImplementation();
        List<Turma> lista_t = turmaDAO.pesquisarPorNome(nomeTurma);
        Turma t = null;
        if (!lista_t.isEmpty()){
            t = lista_t.get(0);
        }

        if (a == null || t == null) {
            throw new Exception("Aluno ou Turma não encontrados");
        }

        aluno.set(a);
        turma.set(t);
        dataMatricula.set(data);
        
        salvar();
    }
    public LongProperty idProperty() {
        return id;
    }

    public ObjectProperty<LocalDate> dataMatriculaProperty() {
        return dataMatricula;
    }

    public ObjectProperty<Aluno> alunoProperty() {
        return aluno;
    }

    public ObjectProperty<Turma> turmaProperty() {
        return turma;
    }

    public StringProperty nomeAlunoProperty() { 
        return nomeAluno;
    }

    public StringProperty nomeTurmaProperty() {
        return nomeTurma; 
    }
}
