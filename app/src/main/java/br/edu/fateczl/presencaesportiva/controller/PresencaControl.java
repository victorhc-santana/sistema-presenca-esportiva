package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.DAO.PresencaDAO;
import br.edu.fateczl.presencaesportiva.DAO.PresencaDAOImplementation;
import br.edu.fateczl.presencaesportiva.model.Presenca;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PresencaControl {
    private ObservableList<Presenca> lista = FXCollections.observableArrayList();
    private PresencaDAO dao = new PresencaDAOImplementation();

    private LongProperty id = new SimpleLongProperty(0);
    private LongProperty alunoId = new SimpleLongProperty(0);
    private LongProperty turmaId = new SimpleLongProperty(0);
    private StringProperty turmaNome = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> dia = new SimpleObjectProperty<>(LocalDate.now());
    private BooleanProperty status = new SimpleBooleanProperty(false);
    
    public ObservableList<Presenca> getLista() {
        return lista;
    }

    public void fromEntity( Presenca presenca ) {
        if (presenca != null) {
            id.set( presenca.getId() );
            alunoId.set( presenca.getAlunoId() );
            turmaId.set( presenca.getTurmaId() );
            dia.set( presenca.getDia() );
            status.set( presenca.getStatus() );
        }
    }

    public void limparCampos() { 
        id.set(0);
        alunoId.set(0);
        turmaId.set(0);
        dia.set(LocalDate.now());
        status.set(false);
    }

    public Presenca toEntity() {
        return new Presenca(
            id.get(),
            alunoId.get(),
            turmaId.get(),
            turmaNome.get(),
             dia.get(),
            status.get()
        );
    }
    public void salvar() {
        Presenca presenca = toEntity();
        if (presenca.getId() == 0) {
            dao.cadastrar(presenca);
        } else {
            dao.atualizar(id.get(), presenca);
        }
        carregar();
    }

    private void carregar() {
        lista.clear();
        lista.addAll(
            dao.pesquisarPorDia("", LocalDate.now()));
    }

    public void pesquisar(String modalidade, LocalDate dia) {
        lista.clear();
        lista.addAll(
            dao.pesquisarPorDia(modalidade, dia));
    }

    public void excluir(int indice) {
        Presenca p = lista.get(indice);
        dao.apagar(p);
        carregar();
    }

    // Getters para as propriedades - usado para o binding
    public LongProperty idProperty() {
        return id;
    }
    public LongProperty alunoIdProperty() {
        return alunoId;
    }
    public LongProperty turmaIdProperty() {
        return turmaId;
    }
    public StringProperty turmaNomeProperty() {
        return turmaNome;
    }
    public ObjectProperty<LocalDate> diaProperty() {
        return dia;
    }
    public BooleanProperty statusProperty() {
        return status;
    }

}
