package br.edu.fateczl.presencaesportiva.view;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class TelaEventos implements Tela {

    @Override
    public Pane render() {

        BorderPane borderPane = new BorderPane(); // Criando um BorderPane
        GridPane gridPane = new GridPane(10, 10);
        gridPane.setAlignment(Pos.CENTER);

        borderPane.setTop(gridPane);

        TextField txtData = new TextField();
        TextField txtTipo = new TextField();
        TextField txtInicio = new TextField();
        TextField txtFim = new TextField();
        TextField txtdiaObservacao = new TextField();
        
        

    }

}
