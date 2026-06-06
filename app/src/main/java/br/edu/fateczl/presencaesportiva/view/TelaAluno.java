package br.edu.fateczl.presencaesportiva.view;

import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.controller.AlunoControl;
import br.edu.fateczl.presencaesportiva.model.Aluno;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.util.StringConverter;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

public class TelaAluno implements Tela {
    @SuppressWarnings("unchecked")
    public Pane render() {

        AlunoControl control = new AlunoControl();

        //inicia border pane para a tela do aluno e um grid pane para os campos de entrada
        BorderPane borderPane = new BorderPane();
        GridPane gridPane= new GridPane(10,10);
        gridPane.setAlignment(Pos.CENTER);

        //adiciona o grid pane ao topo do border pane e a tabela de alunos ao centro
        borderPane.setTop(gridPane);
        TableView<Aluno> tabela = new TableView<>();
        borderPane.setCenter(tabela);

        // Cria os componentes da tela do aluno e os adiciona ao grid pane
        TextField txtNome = new TextField();
        TextField txtCpf = new TextField();
        TextField txtEmail = new TextField();
        TextField txtTelefone = new TextField();
        TextField txtDataNascimento = new TextField();
        TextField txtEndereco = new TextField();
        TextField txtModalidade = new TextField();
        
        //atribui funções aos botões
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.salvar();
            tabela.refresh();
            new Alert(AlertType.INFORMATION, "Aluno registrado com sucesso").show();
        });
        Button btnExcluir = new Button("Excluir");
        btnExcluir.setOnAction(e -> {
            control.excluir();
            tabela.refresh();
        });
        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> {
            TelaMenu telaMenu = new TelaMenu();
            telaMenu.start(new Stage());
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
            tabela.refresh();
        });

        StringConverter<? extends LocalDate> converter = new LocalDateStringConverter();
        
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), control.cpfProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(txtDataNascimento.textProperty(), control.nascimentoProperty(),
             (StringConverter<LocalDate>) converter);
        Bindings.bindBidirectional(txtEndereco.textProperty(), control.enderecoProperty());
        Bindings.bindBidirectional(txtModalidade.textProperty(), control.modalidadeProperty());

        gridPane.add(new Label("Nome do aluno: "), 0, 0);
        gridPane.add(txtNome, 1, 0);
        gridPane.add(new Label("Cpf: "), 0, 1);
        gridPane.add(txtCpf, 1, 1); 
        gridPane.add(new Label("Email: "), 0, 2);
        gridPane.add(txtEmail, 1, 2);
        gridPane.add(new Label("Telefone: "), 0, 3);
        gridPane.add(txtTelefone, 1, 3);
        gridPane.add(new Label("Data de Nascimento: "), 2, 1);
        gridPane.add(txtDataNascimento, 3, 1);
        gridPane.add(new Label("Endereço: "), 2, 2);
        gridPane.add(txtEndereco, 3, 2);
        gridPane.add(new Label("Modalidade: "), 2, 3);
        gridPane.add(txtModalidade, 3, 3);
        gridPane.add(btnSalvar, 0, 4);
        gridPane.add(btnExcluir, 1, 4);
        gridPane.add(btnVoltar, 3, 4);
        gridPane.add(btnPesquisar, 2, 0);

        //Criando as colunas
        TableColumn<Aluno, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(itemData -> 
            new ReadOnlyObjectWrapper<>(itemData.getValue().getId()));
        TableColumn<Aluno, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(itemData ->
            new ReadOnlyStringWrapper(itemData.getValue().getNome()));
        TableColumn<Aluno, String> colCpf = new TableColumn<>("Cpf");
        colCpf.setCellValueFactory(itemData ->
            new ReadOnlyStringWrapper(itemData.getValue().getCpf()));
        TableColumn<Aluno, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(itemData ->
            new ReadOnlyStringWrapper(itemData.getValue().getEmail()));
        TableColumn<Aluno, String> colTelefone = new TableColumn<>("Telefone");
        colTelefone.setCellValueFactory(itemData ->
            new ReadOnlyStringWrapper(itemData.getValue().getTelefone()));
        TableColumn<Aluno, LocalDate> colNascimento = new TableColumn<>("Data de Nascimento");
        colNascimento.setCellValueFactory(itemData ->
            new ReadOnlyObjectWrapper<>(itemData.getValue().getDataNascimento()));
        TableColumn<Aluno, String> colEndereco = new TableColumn<>("Endereço");
        colEndereco.setCellValueFactory(itemData -> 
            new ReadOnlyStringWrapper(itemData.getValue().getEndereco()));      
        TableColumn<Aluno, String> colModalidade = new TableColumn<>("Modalidade");
        colModalidade.setCellValueFactory(itemData -> 
            new ReadOnlyStringWrapper(itemData.getValue().getModalidade()));
        //Adicionando as colunas na tabela
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> control.fromEntity( novo )
        );
        tabela.getColumns().addAll(colId, colNome, colCpf,
             colEmail, colTelefone, colNascimento, colEndereco, colModalidade);
        tabela.setItems(control.getLista());
        //adiciona funções aos botões
        return borderPane;
    }
}
