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
        Button btnAluno = new Button("Cadastrar Aluno");
        Button btnTurma = new Button("Cadastrar turma");
        Button btnPresenca = new Button("Registrar Presença");
        Button btnSair = new Button("Sair");

        /*btnAluno.setOnAction(e -> {
            TelaAluno telaAluno = new TelaAluno();
            telaAluno.start(new Stage());
        });*/
        btnTurma.setOnAction(e -> {
            TelaTurma telaTurma = new TelaTurma();
            telaTurma.start(new Stage());
        });
        btnPresenca.setOnAction(e -> {
            TelaPresenca telaPresenca = new TelaPresenca();
            telaPresenca.start(new Stage());
        });
        btnSair.setOnAction(e -> {
            menu.close();
        });

        gridPane.add(btnAluno, 0, 0);
        gridPane.add(btnTurma, 1, 0);
        gridPane.add(btnPresenca, 0, 1);
        gridPane.add(btnSair, 1, 1);
        //exibe a janela de menu
        menu.show();
    }
}
