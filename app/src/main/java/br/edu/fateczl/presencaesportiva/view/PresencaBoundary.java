package br.edu.fateczl.presencaesportiva.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.fateczl.presencaesportiva.controller.PresencaControl;
import br.edu.fateczl.presencaesportiva.model.Presenca;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
import javafx.util.Callback;


public class PresencaBoundary implements Tela {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DatePicker dtaPresenca = new DatePicker();
    private PresencaControl control = new PresencaControl();
    private TableView<Presenca> tabela = new TableView<>();

    @Override
    public Pane render() {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane(10,10);
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setTop(gridPane);
        borderPane.setCenter(tabela);

        TextField txtTurma = new TextField();
        
        Button btnLimparCampos = new Button();
        Image iconNew = new Image(getClass().getResourceAsStream("/images/apagar.png"));
        ImageView imgViewNew = new ImageView( iconNew );
        imgViewNew.setFitWidth(16);
        imgViewNew.setFitHeight(16);
        btnLimparCampos.setGraphic( imgViewNew );
        btnLimparCampos.setOnAction( e -> control.limparCampos());

        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
        LocalDate dia = dtaPresenca.getValue();
        String nomeTurma = txtTurma.getText();
        if (dia == null || nomeTurma.isBlank()) {
            new Alert(Alert.AlertType.WARNING, "Preencha a turma e a data.").show();
            return;
        }
        control.carregarPorTurmaEDia(nomeTurma, dia);
        tabela.refresh();
        });

        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
        control.salvarTodos();
        new Alert(Alert.AlertType.INFORMATION, "Presenças salvas!").show();
        });

        gridPane.add(new Label("Data da Presença:"), 0, 0);
        gridPane.add(dtaPresenca, 1, 0);
        gridPane.add(new Label("Turma:"), 0, 1);
        gridPane.add(txtTurma, 1, 1);
        gridPane.add(btnPesquisar, 3, 0);
        gridPane.add(btnSalvar, 1, 2);
        gridPane.add(btnLimparCampos, 3, 1);

        Callback<TableColumn<Presenca, Boolean>, TableCell<Presenca, Boolean>> cellFactory = new Callback<>() {
        @Override
        public TableCell<Presenca, Boolean> call(TableColumn<Presenca, Boolean> param) {
            return new TableCell<>() {
                CheckBox cb = new CheckBox();
                {
                    cb.setOnAction(e -> {
                    // pega a linha correspondente e atualiza o valor
                    Presenca row = getTableView().getItems().get(getIndex());
                    row.setStatus(cb.isSelected());
                });
                }

            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty); // obrigatório chamar o super
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    cb.setSelected(item); // sincroniza o estado do checkbox com o valor da linha
                    setGraphic(cb);
                }
            }
            };
        }
        };

        //Criando as colunas
        TableColumn<Presenca, Long> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(itemData ->
             new javafx.beans.property.SimpleLongProperty(itemData.getValue().getId()).asObject());

        TableColumn<Presenca, String> colAluno = new TableColumn<>("Aluno");
        colAluno.setCellValueFactory(itemData ->
             new javafx.beans.property.SimpleStringProperty(itemData.getValue().getMatricula().getAluno().getNome()));

        TableColumn<Presenca, String> colTurma = new TableColumn<>("Turma");
        colTurma.setCellValueFactory(itemData ->
             new javafx.beans.property.SimpleStringProperty(itemData.getValue().getMatricula().getTurma().getNome()));

        TableColumn<Presenca, String> colData = new TableColumn<>("Data");
        colData.setCellValueFactory(itemData ->
             new javafx.beans.property.SimpleStringProperty(itemData.getValue().getDia().format(dtf)));

        TableColumn<Presenca, Boolean> colStatus = new TableColumn<>("Presente");
        colStatus.setCellValueFactory(itemData ->
            new javafx.beans.property.SimpleBooleanProperty(itemData.getValue().getStatus()).asObject());
        colStatus.setCellFactory(cellFactory);

        //Adicionando as colunas na tabela
        tabela.getSelectionModel().selectedItemProperty().addListener(
            (obj, antigo, novo) -> control.fromEntity( novo )
        );

        tabela.getColumns().addAll(colId, colAluno, colTurma, colData, colStatus);
        tabela.setItems(control.getLista());


        return borderPane;
    }

}
