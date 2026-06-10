package br.edu.fateczl.presencaesportiva.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.edu.fateczl.presencaesportiva.controller.PresencaControl;
import br.edu.fateczl.presencaesportiva.model.Presenca;
import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class PresencaBoundary implements Tela {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DatePicker dtaPresenca = new DatePicker();
    private PresencaControl control = new PresencaControl();
    private TableView<Presenca> tabela = new TableView<>();

    @Override
    public Pane render() {
        BorderPane borderPane = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        borderPane.setTop(gridPane);

        TextField txtTurma = new TextField();
        TextField txtModalidade = new TextField();
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            LocalDate dia = dtaPresenca.getValue();
            String modalidade = txtModalidade.getText();
            control.pesquisar(modalidade, dia);
        });
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.salvar();
            tabela.refresh();
        });

        gridPane.add(new Label("Data da Presença:"), 0, 0);
        gridPane.add(dtaPresenca, 1, 0);
        gridPane.add(new Label("Turma:"), 0, 1);
        gridPane.add(txtTurma, 1, 1);
        gridPane.add(btnPesquisar, 3, 0);
        gridPane.add(btnSalvar, 3, 1);

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


        //Adicionando as colunas na tabela
        tabela.getColumns().addAll(colId, colAluno, colTurma, colData);
        borderPane.setCenter(tabela);

        tabela.getColumns().addAll(colId, colAluno, colTurma, colData);
        tabela.setItems(control.getLista());
        
        return borderPane;
    }

}
