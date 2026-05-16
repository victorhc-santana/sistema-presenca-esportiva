package br.edu.fateczl.presencaesportiva.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaMenu extends Application{
    @Override
    public void start(Stage menu) {
        //inicia painel de grid para a tela de menu e poisiciona no centro
        GridPane gridPane = new GridPane(2, 3);
        gridPane.setAlignment(Pos.CENTER);
        //configura a cena e o título da janela
        menu.setTitle("Tela de Menu");
        menu.setScene(new Scene(gridPane, 400, 300));
        //cria os componentes da tela de menu e os adiciona ao painel de grid
        Button btnPresenca = new Button("Registrar Presença");
        Button btnRelatorio = new Button("Gerar Relatório");

        gridPane.add(btnPresenca, 0, 0);
        gridPane.add(btnRelatorio, 1, 0);
        //exibe a janela de menu
        menu.show();
    }
}
