package br.edu.fateczl.presencaesportiva.view;

import javafx.scene.control.TableCell;
import br.edu.fateczl.presencaesportiva.controller.MatriculaControl;
import br.edu.fateczl.presencaesportiva.model.Matricula;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.geometry.Pos;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.image.ImageView;

public class MatriculaBoundary implements Tela {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Override
    public Pane render() {

        MatriculaControl control = new MatriculaControl();

        BorderPane borderPane = new BorderPane(); // Criando um BorderPane
        GridPane gridPane = new GridPane(10, 10);
        gridPane.setAlignment(Pos.CENTER);

        borderPane.setTop(gridPane);

        TextField txtAluno = new TextField();
        TextField txtTurma = new TextField();
        DatePicker dtaMatricula = new DatePicker();

        TableView<Matricula> tabela = new TableView<>();
        borderPane.setCenter(tabela);

        //Criando o botão Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            try {
                control.salvarPorNome(txtAluno.getText(), txtTurma.getText(), dtaMatricula.getValue());
                tabela.refresh();
                new Alert(AlertType.INFORMATION, "Matricula registrada com sucesso").show();
            } catch (Exception ex) {
                new Alert(AlertType.ERROR, ex.getMessage()).show();
            }
        });
        //Criando o botão Pesquisar
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            control.pesquisar(txtAluno.getText());
            tabela.refresh();
        });

        Button btnLimparCampos = new Button();
        Image iconNew = new Image(getClass().getResourceAsStream("/images/apagar.png"));
        ImageView imgViewNew = new ImageView( iconNew );
        imgViewNew.setFitWidth(16);
        imgViewNew.setFitHeight(16);
        btnLimparCampos.setGraphic( imgViewNew );
        btnLimparCampos.setOnAction( e -> control.limparCampos());

        Label lblAluno = new Label("Aluno:");
        Label lblTurma = new Label("Turma:");
        Label lblDataMatricula = new Label("Data matricula:");

        Bindings.bindBidirectional(dtaMatricula.valueProperty(), control.dataMatriculaProperty());
        Bindings.bindBidirectional(txtAluno.textProperty(), control.nomeAlunoProperty());
        Bindings.bindBidirectional(txtTurma.textProperty(), control.nomeTurmaProperty());

        // Posicionando no gridPane todos os textFields e botões
        gridPane.add(lblAluno, 0, 0);
        gridPane.add(txtAluno, 1, 0);
        gridPane.add(lblTurma, 0, 1);
        gridPane.add(txtTurma, 1, 1);
        gridPane.add(lblDataMatricula, 0, 2);
        gridPane.add(dtaMatricula, 1, 2);
        gridPane.add(btnSalvar,0,3);
        gridPane.add(btnPesquisar,2,0);
        gridPane.add(btnLimparCampos,3,0);
    
        TableColumn<Matricula, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getId()));
        // Aluno
        TableColumn<Matricula, String> colAluno = new TableColumn<>("Aluno");
        colAluno.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getAluno().getNome()));
        // Turma
        TableColumn<Matricula, String> colTurma = new TableColumn<>("Turma");
        colTurma.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getTurma().getNome()));
        // Data Matricula
        TableColumn<Matricula, String> colDataMatricula = new TableColumn<>("Data matricula");
        colDataMatricula.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDataMatricula().format(dtf)));
        //Ações que podem ser feitas para cada linha da tabela
        TableColumn<Matricula, Void> colAcao = new TableColumn<>("Ações");

        tabela.getSelectionModel().selectedItemProperty().addListener(
        (obj, antigo, novo) -> control.fromEntity(novo));

        tabela.getColumns().addAll(colId, colAluno, colTurma, colDataMatricula, colAcao);
        tabela.setItems(control.getLista());

        Callback<TableColumn<Matricula, Void>, TableCell<Matricula, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Matricula, Void> call(final TableColumn<Matricula, Void> param) {
                return new TableCell<>() {
                    Button btnExcluir = new Button("Excluir");
                    {
                        Image icondelete = new Image(getClass().getResourceAsStream("/images/exclusao.png"));
                        ImageView iconView = new ImageView(icondelete);
                        iconView.setFitWidth(16);
                        iconView.setFitHeight(16);
                        btnExcluir.setGraphic(iconView);
                        btnExcluir.setOnAction((e) -> {
                            // da a mensagem de confirmação para o usuário antes de excluir a turma
                            Alert alert = new Alert(AlertType.CONFIRMATION,
                                    "Tem certeza que deseja excluir esta matricula?", ButtonType.YES, ButtonType.NO);
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
        colAcao.setCellFactory(cellFactory);
        return borderPane;
        }

}
