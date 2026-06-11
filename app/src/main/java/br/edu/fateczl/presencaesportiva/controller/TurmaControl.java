package br.edu.fateczl.presencaesportiva.controller;

import br.edu.fateczl.presencaesportiva.model.Turma;

import br.edu.fateczl.presencaesportiva.DAO.turmaDAO;
import br.edu.fateczl.presencaesportiva.DAO.turmaDAOImplementation;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TurmaControl {

    private ObservableList<Turma> lista = FXCollections.observableArrayList();
    private turmaDAO dao = new turmaDAOImplementation();

    // Preciso ver o que está errado nesse passo tipo Integer
    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty modalidade = new SimpleStringProperty("");
    private StringProperty professor = new SimpleStringProperty("");
    private StringProperty horario = new SimpleStringProperty("");
    private StringProperty diaSemana = new SimpleStringProperty("");
    private IntegerProperty vagasTotal = new SimpleIntegerProperty(0);
    private StringProperty nivel = new SimpleStringProperty("");

    public ObservableList<Turma> getLista() {
        return lista;
    }

    public void fromEntity(Turma turma) {
        if (turma != null) {
            id.set(turma.getId());
            nome.set(turma.getNome());
            modalidade.set(turma.getModalidade());
            professor.set(turma.getProfessor());
            horario.set(turma.getHorario());
            diaSemana.set(turma.getDiaSemana());
            vagasTotal.set(turma.getVagasTotal());
            nivel.set(turma.getNivel());
        }
    }

    // Método para limpar todos os campos
    //
    public void limparCampos() {
        id.set(0);
        nome.set("");
        modalidade.set("");
        professor.set("");
        horario.set("");
        diaSemana.set("");
        vagasTotal.set((Integer) 0);
        nivel.set("");
    }

    public Turma toEntity() {
        Turma turma = new Turma();
        turma.setId(id.get());
        turma.setNome(nome.get());
        turma.setModalidade(modalidade.get());
        turma.setProfessor(professor.get());
        turma.setHorario(horario.get());
        turma.setDiaSemana(diaSemana.get());
        turma.setVagasTotal(vagasTotal.get());
        turma.setNivel(nivel.get());

        return turma;

    }

    public void salvar() {
        Turma turma = toEntity();

        if (id.get() > 0) {
            dao.atualizar(id.get(), turma);
        } else {
            dao.cadastrar(turma);
        }
        limparCampos();
        carregar();
    }

    private void carregar() {
        lista.clear();
        lista.addAll(
                dao.pesquisarPorNome("") // Pesquisa por nome da modalidade
        );
    }

    public void pesquisar() {
        lista.clear();
        lista.addAll(
                dao.pesquisarPorNome(nomeProperty().get()));
    }

    public void excluir(int indice) {
        Turma a = lista.get(indice);
        dao.apagar(a);
        carregar();
    }

    public LongProperty idProperty() {
        return id;
    }

    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty modalidadeProperty() {
        return modalidade;
    }

    public StringProperty professorProperty() {
        return professor;
    }

    public StringProperty horarioProperty() {
        return horario;
    }

    public StringProperty diaSemanaProperty() {
        return diaSemana;
    }

    public IntegerProperty vagasTotalProperty() {
        return vagasTotal;
    }

    public StringProperty nivelProperty() {
        return nivel;
    }

}
