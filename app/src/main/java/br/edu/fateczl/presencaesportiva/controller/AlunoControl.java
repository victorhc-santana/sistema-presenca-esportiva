package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.model.Aluno;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {
    private ObservableList<Aluno> lista = FXCollections.observableArrayList();
    private StringProperty nome = new SimpleStringProperty("");
    private Property  = new SimpleStringProperty("");
    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    public ObservableList<Aluno> getLista() {
        return lista;
    }
}
