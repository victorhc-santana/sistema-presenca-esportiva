package br.edu.fateczl.presencaesportiva.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class TelaLogin extends Application {

    @Override
    public void start(Stage login) {
        //inicia painel de grid para a tela de login e poisiciona no centro
        GridPane gridPane = new GridPane(2, 3);
        gridPane.setAlignment(Pos.CENTER);
        //configura a cena e o título da janela
        login.setTitle("Tela de Login");
        login.setScene(new Scene(gridPane, 400, 300));
        //cria os componentes da tela de login e os adiciona ao painel de grid
        Label lblUsername = new Label("Username:");
        TextField txtUsername = new TextField();
        Label lblPassword = new Label("Password:");
        PasswordField txtPassword = new PasswordField();
        Button btnLogin = new Button("Login");

        gridPane.add(lblUsername, 0, 0);
        gridPane.add(txtUsername, 1, 0);
        gridPane.add(lblPassword, 0, 1);
        gridPane.add(txtPassword, 1, 1);
        gridPane.add(btnLogin, 1, 2);
        //exibe a janela de login
        login.show();
    }
    
}

