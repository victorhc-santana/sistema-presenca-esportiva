package br.edu.fateczl.presencaesportiva.view;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import br.edu.fateczl.presencaesportiva.controller.AlunoControl;
import br.edu.fateczl.presencaesportiva.model.Aluno;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.util.Callback;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class AlunoBoundary implements Tela {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DatePicker dtaLancamento = new DatePicker();
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
        TextField txtEndereco = new TextField();
        TextField txtModalidade = new TextField();
        
        //atribui funções aos botões
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.salvar();
            tabela.refresh();
            new Alert(AlertType.INFORMATION, "Aluno registrado com sucesso").show();
        });
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
            tabela.refresh();
        });
        
        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtCpf.textProperty(), control.cpfProperty());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.emailProperty());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefoneProperty());
        Bindings.bindBidirectional(dtaLancamento.valueProperty(), control.nascimentoProperty());
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
        gridPane.add(dtaLancamento, 3, 1);
        gridPane.add(new Label("Endereço: "), 2, 2);
        gridPane.add(txtEndereco, 3, 2);
        gridPane.add(new Label("Modalidade: "), 2, 3);
        gridPane.add(txtModalidade, 3, 3);
        gridPane.add(btnSalvar, 0, 4);
        gridPane.add(btnPesquisar, 2, 0);

        //Criando as colunas
        TableColumn<Aluno, Long> colId = new TableColumn<>("ID");
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
        TableColumn<Aluno, String> colNascimento = new TableColumn<>("Data de Nascimento");
        colNascimento.setCellValueFactory(itemData ->
            new ReadOnlyStringWrapper(itemData.getValue().getDataNascimento().format(dtf)));
        TableColumn<Aluno, String> colEndereco = new TableColumn<>("Endereço");
        colEndereco.setCellValueFactory(itemData -> 
            new ReadOnlyStringWrapper(itemData.getValue().getEndereco()));      
        TableColumn<Aluno, String> colModalidade = new TableColumn<>("Modalidade");
        colModalidade.setCellValueFactory(itemData -> 
            new ReadOnlyStringWrapper(itemData.getValue().getModalidade()));
        TableColumn<Aluno, Void> colAcoes = new TableColumn<>("Ações");

        //Adicionando as colunas na tabela
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> control.fromEntity( novo )
        );

        tabela.getColumns().addAll(colId, colNome, colCpf,
             colEmail, colTelefone, colNascimento, colEndereco, colModalidade, colAcoes);
        tabela.setItems(control.getLista());
        //adiciona exclusão de aluno

        //instancia o cell factory para criar um botão de exclusão em cada linha da tabela
        Callback<TableColumn<Aluno, Void>, TableCell<Aluno, Void>> cellFactory = new Callback<>() {
            @Override

            public TableCell<Aluno, Void> call(final TableColumn<Aluno, Void> param) {
                return new TableCell<>() {
                    Button btnExcluir = new Button("Excluir");
                    {   
                        Image icondelete = new Image(getClass().getResourceAsStream("/images/exclusao.png"));
                        ImageView iconView = new ImageView(icondelete);
                        iconView.setFitWidth(16);
                        iconView.setFitHeight(16);
                        btnExcluir.setGraphic(iconView);
                        btnExcluir.setOnAction((e) -> {
                            //da a mensagem de confirmação para o usuário antes de excluir o aluno 
                            Alert alert = new Alert(AlertType.CONFIRMATION,
                                 "Tem certeza que deseja excluir este aluno?"
                                , ButtonType.YES, ButtonType.NO);

                            Optional<ButtonType> result = alert.showAndWait();
                            
                            if (result.isPresent() && result.get() == ButtonType.YES) {
                                control.excluir(getIndex());
                            }
                        });
                    }
                    public void updateItem(Void parm, boolean empty) {
                        
                        if (!empty) {
                            setGraphic(btnExcluir);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        };
        colAcoes.setCellFactory(cellFactory);
        return borderPane;
    }
}
