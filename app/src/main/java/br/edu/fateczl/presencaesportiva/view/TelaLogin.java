package br.edu.fateczl.presencaesportiva.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaLogin extends Application {

    @Override
    public void start(Stage login) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        login.setTitle("Tela de Login");
        login.setScene(new Scene(gridPane, 400, 300));
        login.show();
    }
    
}

