package br.edu.fateczl.presencaesportiva.view;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {
    private BorderPane pane = new BorderPane();
    private Pane alunoPane = new AlunoBoundary().render();


    @Override
    public void start(Stage stage) { 
        Scene scn = new Scene(pane, 800, 600);
        pane.setCenter( alunoPane );

        MenuBar menuBar = new MenuBar();

        Menu mnuArquivo = new Menu("Arquivo");
        Menu mnuCadastro = new Menu("Cadastro");
        Menu mnuAjuda = new Menu("Ajuda");

        MenuItem mnuAlunoItem = new MenuItem("Alunos");
        MenuItem mnuFilmeItem = new MenuItem("Filmes");

        menuBar.getMenus().addAll( mnuArquivo, mnuCadastro, mnuAjuda);

        mnuCadastro.getItems().addAll( mnuAlunoItem, mnuFilmeItem );

        pane.setTop( menuBar );


        mnuAlunoItem.setOnAction( e -> pane.setCenter( alunoPane ) );

        stage.setScene(scn);
        stage.setTitle("Presença Esportiva");
        stage.show();
    }
    
}
