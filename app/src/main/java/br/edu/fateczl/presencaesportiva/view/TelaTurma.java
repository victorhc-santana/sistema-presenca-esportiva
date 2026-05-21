package br.edu.fateczl.presencaesportiva.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaTurma {
    public void start(Stage telaTurma) {
        //inicia painel de grid para a tela de turma e poisiciona no centro
        GridPane gridPane = new GridPane(2, 3);
        gridPane.setAlignment(Pos.CENTER);
        //configura a cena e o título da janela
        telaTurma.setTitle("Tela de Turma");
        telaTurma.setScene(new Scene(gridPane, 400, 300));
        //cria os componentes da tela de turma e os adiciona ao painel de grid
        Button btnSalvar = new Button("Salvar");
        Button btnExcluir = new Button("Excluir");
        Button btnVoltar = new Button("Voltar");
        Button btnPesquisar = new Button("Pesquisar");

        btnVoltar.setOnAction(e -> {
            telaTurma.close();
        });

        gridPane.add(btnSalvar, 0, 0);
        gridPane.add(btnExcluir, 1, 0);
        gridPane.add(btnPesquisar, 0, 1);
        gridPane.add(btnVoltar, 1, 1);
        //exibe a janela de turma
        telaTurma.show();
    }
}
