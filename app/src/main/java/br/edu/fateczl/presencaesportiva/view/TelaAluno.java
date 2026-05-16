package br.edu.fateczl.presencaesportiva.view;

//import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.model.Aluno;
//import br.edu.fateczl.presencaesportiva.model.Usuario;
import javafx.application.Application;
//import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaAluno extends Application {
    public void start(Stage aluno) {
        //inicia border pane para a tela do aluno e um grid pane para os campos de entrada
        BorderPane borderPane = new BorderPane();
        GridPane gridPane= new GridPane(10,10);
        gridPane.setAlignment(Pos.CENTER);
        //adiciona o grid pane ao topo do border pane e a tabela de alunos ao centro
        borderPane.setTop(gridPane);
        TableView<Aluno> tabela = new TableView<>();
        borderPane.setCenter(tabela);

        // Configura a cena e o título da janela
        aluno.setTitle("Tela do Aluno");
        aluno.setScene(new Scene(borderPane, 800, 600));

        // Cria os componentes da tela do aluno e os adiciona ao grid pane
        Label lblNome = new Label("Nome do Aluno:");
        TextField txtNome = new TextField();
        Label lblCpf = new Label("CPF:");
        TextField txtCpf = new TextField();
        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        Label lblTelefone = new Label("Telefone:");
        TextField txtTelefone = new TextField();
        Label lblDataNascimento = new Label("Data de Nascimento:");
        TextField txtDataNascimento = new TextField();
        Label lblEndereco = new Label("Endereço:");
        TextField txtEndereco = new TextField();
        Label lblModalidade = new Label("Modalidade:");
        TextField txtModalidade = new TextField();
        Button btnSalvar = new Button("Salvar");
        Button btnExcluir = new Button("Excluir");
        Button btnVoltar = new Button("Voltar");
        Button btnPesquisar = new Button("Pesquisar");
        /*
        Bindings.bindBidirectional(txtNome.textProperty(), control.tituloProperty());
        Bindings.bindBidirectional(txtGenero.textProperty(), control.generoProperty());
        Bindings.bindBidirectional(txtLancamento.textProperty(), control.lancamentoProperty(),
             (StringConverter<LocalDate>) converter); */

        gridPane.add(lblNome, 0, 0);
        gridPane.add(txtNome, 1, 0);
        gridPane.add(lblCpf, 0, 1);
        gridPane.add(txtCpf, 1, 1); 
        gridPane.add(lblEmail, 0, 2);
        gridPane.add(txtEmail, 1, 2);
        gridPane.add(lblTelefone, 0, 3);
        gridPane.add(txtTelefone, 1, 3);
        gridPane.add(lblDataNascimento, 2, 1);
        gridPane.add(txtDataNascimento, 3, 1);
        gridPane.add(lblEndereco, 2, 2);
        gridPane.add(txtEndereco, 3, 2);
        gridPane.add(lblModalidade, 2, 3);
        gridPane.add(txtModalidade, 3, 3);
        gridPane.add(btnSalvar, 0, 4);
        gridPane.add(btnExcluir, 1, 4);
        gridPane.add(btnVoltar, 3, 4);
        gridPane.add(btnPesquisar, 2, 0);

        
        // Lógica para exibir a tela do aluno
        aluno.show();
    }
}
