/*adicionado funções de salvar e pesquisar, procurar como atualizar e remover*/

package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.model.Aluno;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl {
    private ObservableList<Aluno> lista = FXCollections.observableArrayList();
    private IntegerProperty id = new SimpleIntegerProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty modalidade = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty cpf = new SimpleStringProperty(""); 
    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    public ObservableList<Aluno> getLista() {
        return lista;
    }
    
     public void fromEntity( Aluno aluno ) {
        if (aluno != null) {
            id.set( aluno.getId() );
            nome.set( aluno.getNome() );
            cpf.set( aluno.getCpf() );
            nascimento.set( aluno.getDataNascimento() );
            email.set( aluno.getEmail() );
            telefone.set( aluno.getTelefone() );
            modalidade.set( aluno.getModalidade() );
            endereco.set( aluno.getEndereco() );
        }
    }

    public Aluno toEntity() {
        Aluno aluno = new Aluno();
        aluno.setId( id.get() );
        aluno.setNome( nome.get() );
        aluno.setCpf( cpf.get() );
        aluno.setDataNascimento( nascimento.get() );
        aluno.setEmail( email.get() );
        aluno.setTelefone( telefone.get() );
        aluno.setModalidade( modalidade.get() );
        aluno.setEndereco( endereco.get() );
        return aluno;
    }

    public void salvar(){
        lista.add( toEntity() );
    }

    public Aluno pesquisar(){
        for (Aluno aluno : lista) {
            if (aluno.getId() == id.get()) {
                return aluno;
            }
        }
        return null;
    }

    public void excluir(){
        Aluno aluno = pesquisar();
        if (aluno != null) {
            lista.remove(aluno);
        }
    }
    
    public StringProperty nomeProperty() {
        return nome;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty telefoneProperty() {
        return telefone;
    }

    public StringProperty modalidadeProperty() {
        return modalidade;
    }

    public StringProperty enderecoProperty() {
        return endereco;
    }

    public StringProperty cpfProperty() {
        return cpf;
    }

    public ObjectProperty<LocalDate> nascimentoProperty() {
        return nascimento;
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
}
