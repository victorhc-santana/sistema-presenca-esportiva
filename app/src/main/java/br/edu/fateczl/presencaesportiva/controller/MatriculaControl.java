package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;

import org.jspecify.annotations.NonNull;

import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAO;
import br.edu.fateczl.presencaesportiva.DAO.MatriculaDAOImplementation;
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
    private MatriculaDAOImplementation dao = new MatriculaDAOImplementation();

    private LongProperty id = new SimpleLongProperty(0);
    private ObjectProperty<Aluno> aluno = new SimpleObjectProperty<>(null);
    private ObjectProperty<Turma> turma = new SimpleObjectProperty<>(null);
    private ObjectProperty<LocalDate> dataMatricula = new SimpleObjectProperty<>(null);

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
        matriculas.addAll(
                dao.pesquisarPorDia(""));
    }

    private void pesquisar() {
        matriculas.clear();
        matriculas.addAll(
                dao.pesquisarPorDia(dataMatriculaProperty().get()));
    }

    public void excluir(int indice) {
        Matricula a = matriculas.get(indice);
        dao.apagar(a);
        carregar();
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

}
