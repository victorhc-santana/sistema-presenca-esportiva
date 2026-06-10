package br.edu.fateczl.presencaesportiva.view;

import java.util.Optional;

import br.edu.fateczl.presencaesportiva.model.Turma;
import br.edu.fateczl.presencaesportiva.controller.TurmaControl;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.util.Callback;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class TurmaBoundary implements Tela {

    // Criando um stage para configuração da tela

    @Override
    public Pane render() {

        TurmaControl control = new TurmaControl();

        // Criando um BorderPane
        BorderPane borderPane = new BorderPane(); // Criando um BorderPane
        GridPane gridPane = new GridPane(10, 10);
        gridPane.setAlignment(Pos.CENTER);

        borderPane.setTop(gridPane); // Colocando o gridPane na parte do topo do borderPane

        // Criando os textField com os dados da telaTurma

        TextField txtNome = new TextField();
        TextField txtModalidade = new TextField();
        TextField txtProfessor = new TextField();
        TextField txtHorario = new TextField();
        TextField txtdiaSemana = new TextField();
        TextField txtvagasTotal = new TextField();
        TextField txtNivel = new TextField();

        TableView<Turma> tabela = new TableView<>();
        borderPane.setCenter(tabela);

        // Criando o botão Salvar
        Button btnSalvar = new Button("Salvar");
        btnSalvar.setOnAction(e -> {
            control.salvar();
            tabela.refresh();
            new Alert(AlertType.INFORMATION, "turma registrada com sucesso").show();
        });

        // Criando o botão Pesquisar
        Button btnPesquisar = new Button("Pesquisar");
        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
            tabela.refresh();
        });

        Bindings.bindBidirectional(txtNome.textProperty(), control.nomeProperty());
        Bindings.bindBidirectional(txtModalidade.textProperty(), control.modalidadeProperty());
        Bindings.bindBidirectional(txtProfessor.textProperty(), control.professorProperty());
        Bindings.bindBidirectional(txtHorario.textProperty(), control.horarioProperty());
        Bindings.bindBidirectional(txtdiaSemana.textProperty(), control.diaSemanaProperty());
        // Bindings.bindBidirectional(txtvagasTotal.IntegerProperty(),
        // control.vagasTotalProperty());
        Bindings.bindBidirectional(txtNivel.textProperty(), control.nivelProperty());

        Label lblNome = new Label("Nome turma:");
        Label lblModalidade = new Label("Modalidade:");
        Label lblProfessor = new Label("Professor:");
        Label lblHorario = new Label("Horario:");
        Label lbldiaSemana = new Label("Dia da semana:");
        Label lblvagasTotal = new Label("Vagas totais:");
        Label lblNivel = new Label("Nível turma:");

        // Posicionando no gridPane todos os textFields e botões
        gridPane.add(lblNome, 0, 0);
        gridPane.add(txtNome, 1, 0);
        gridPane.add(lblModalidade, 0, 1);
        gridPane.add(txtModalidade, 1, 1);
        gridPane.add(lblProfessor, 0, 2);
        gridPane.add(txtProfessor, 1, 2);
        gridPane.add(lblHorario, 0, 3);
        gridPane.add(txtHorario, 1, 3);
        gridPane.add(lbldiaSemana, 2, 1);
        gridPane.add(txtdiaSemana, 3, 1);
        gridPane.add(lblvagasTotal, 2, 2);
        gridPane.add(txtvagasTotal, 3, 2);
        gridPane.add(lblNivel, 2, 3);
        gridPane.add(txtNivel, 3, 3);
        gridPane.add(btnPesquisar, 3, 0);
        gridPane.add(btnSalvar, 0, 4);

        // Criando as colunas da tableview

        // ID
        TableColumn<Turma, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getId()));
        // Nome
        TableColumn<Turma, String> colNome = new TableColumn<>("Nome");
        colNome.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNome()));
        // Modalidade
        TableColumn<Turma, String> colModalidade = new TableColumn<>("Modalidade");
        colModalidade.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getModalidade()));
        // Professor
        TableColumn<Turma, String> colProfessor = new TableColumn<>("Professor");
        colProfessor.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getProfessor()));
        // Horario
        TableColumn<Turma, String> colHorario = new TableColumn<>("Horario");
        colHorario.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getHorario()));
        // Dia da Semana
        TableColumn<Turma, String> coldiaSemana = new TableColumn<>("Dia da semana");
        coldiaSemana.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getDiaSemana()));
        // Total Vagas
        TableColumn<Turma, Integer> colTotalVagas = new TableColumn<>("Total Vagas");
        colTotalVagas.setCellValueFactory(itemData -> new ReadOnlyObjectWrapper<>(itemData.getValue().getVagasTotal()));
        // Nivel
        TableColumn<Turma, String> colNivel = new TableColumn<>("Nivel turma");
        colNivel.setCellValueFactory(itemData -> new ReadOnlyStringWrapper(itemData.getValue().getNivel()));
        // Ações que podem ser feitas para cada linha da tabela
        TableColumn<Turma, Void> colAcao = new TableColumn<>("Ações");

        // Após as colunas serem criadas, elas são adicionadas na TableView

        tabela.getSelectionModel().selectedItemProperty().addListener(
                (obj, antigo, novo) -> control.fromEntity(novo));

        tabela.getColumns().addAll(colId, colNome, colModalidade, colProfessor, colHorario, coldiaSemana, colTotalVagas,
                colNivel, colAcao);
        tabela.setItems(control.getLista());

        // Criando um tipo Callback ==> Copiar e colar da classe AlunoBoundary
        Callback<TableColumn<Turma, Void>, TableCell<Turma, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Turma, Void> call(final TableColumn<Turma, Void> param) {
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
                                    "Tem certeza que deseja excluir esta turma?", ButtonType.YES, ButtonType.NO);
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
        //return borderPane; //pq retorna isso ?
        // Colocando o titulo na tela
        return borderPane;
    } // chave que fecha a classe principal

}