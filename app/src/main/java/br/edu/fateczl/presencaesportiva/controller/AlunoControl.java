/*Nome do autor: Victor Hugo Campos Santana
Nota do autor: O codigo aqui presente foi feito com a ideia de estudar e compreender a matéria
de POO, está sujeita a erros e falhas, podendo ser Otimizado. Inteligencia Aritifical generativa (Claude IA e ChatGPT) 
foi utilizada para auxiliar a compreensão e organização do código em auxilio com os materiais 
providenciados pelo professor. O codigo não foi gerado por IA generativa.
*/

package br.edu.fateczl.presencaesportiva.controller;

import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.DAO.alunoDAO;
import br.edu.fateczl.presencaesportiva.DAO.alunoDAOImplementation;
import br.edu.fateczl.presencaesportiva.model.Aluno;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AlunoControl  {
    
    /*Lista Observavel, table view - material disponivel nos slides "Observer: padrão de projetos"
    tem como objetivo criar uma lista observavel que notifica automaticamente o table view
    quando a lista é modificada, permitindo que a interface gráfica seja atualizada em tempo real.*/
    private ObservableList<Aluno> lista = FXCollections.observableArrayList();
    private alunoDAO dao = new alunoDAOImplementation();


    /*Propriedades de JavaFX - vinculadas a tela via binding, material disponivel nos slides "13-table view"*/
    private LongProperty id = new SimpleLongProperty(0);
    private StringProperty nome = new SimpleStringProperty("");
    private StringProperty email = new SimpleStringProperty("");
    private StringProperty telefone = new SimpleStringProperty("");
    private StringProperty modalidade = new SimpleStringProperty("");
    private StringProperty endereco = new SimpleStringProperty("");
    private StringProperty cpf = new SimpleStringProperty(""); 
    private ObjectProperty<LocalDate> nascimento = new SimpleObjectProperty<>(LocalDate.now());

    //Getter da lista -usado para tableview
    public ObservableList<Aluno> getLista() {
        return lista;
    }

    //fromEntity carrega os dados de um aluno na tela, os convertendo adequadamente
    //utilizado em atualizar()
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

    public void limparCampos() { 
        id.set(0);
        nome.set("");
        cpf.set("");
        nascimento.set(LocalDate.now());
        email.set("");
        telefone.set("");
        modalidade.set("");
        endereco.set("");
    }

    //toEntity converte os dados da tela para um objeto do tipo Aluno
    //utilizado em salvar()
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

    //-----------------------------------------
    //operações de CRUD - Create, Read, Update, Delete
    //-----------------------------------------
    public void salvar(){
        Aluno aluno = toEntity();
        System.out.println("ID do Filme ==> " + aluno.getId());
        if (id.get() > 0) { 
            dao.atualizar(id.get(), aluno);
        } else { 
            dao.cadastrar( aluno );
        }
        limparCampos();
        carregar();
    }

    private void carregar() {
       lista.clear();
        lista.addAll( 
            dao.pesquisarPorNome("")
        );
    }

    public void pesquisar(){
         lista.clear();
        lista.addAll( 
            dao.pesquisarPorNome( nomeProperty().get())
        );
    }
    //atualizar a função de excluir com o visto em sala
    public void excluir(int indice){
        Aluno a = lista.get( indice );
        dao.apagar(a);
        carregar();
    }
    

    //getter das propriedades - usado para o binding
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
    
    public LongProperty idProperty() {
        return id;
    }
}
