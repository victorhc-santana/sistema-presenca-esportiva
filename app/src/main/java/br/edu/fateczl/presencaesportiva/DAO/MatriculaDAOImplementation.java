package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Aluno;
import br.edu.fateczl.presencaesportiva.model.Matricula;

public class MatriculaDAOImplementation implements MatriculaDAO {

    private static final String DB_URI = "jdbc:mariadb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public MatriculaDAOImplementation() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver Carregado...");
            con = DriverManager.getConnection(DB_URI, DB_USER, DB_PASS);
            System.out.println("Conectado no banco de dados...");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco de dados");
            e.printStackTrace();
        }
    }

    @Override
    public void cadastrar(Matricula a) {
        try {
            String sql = "INSERT INTO matricula (aluno, turma, data_matricula) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            stmt.setString(2, a.getAluno().getNome());
            stmt.setString(3, a.getTurma().getNome());
            stmt.setDate(4, java.sql.Date.valueOf(a.getDataMatricula()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar matricula");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Matricula f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'apagar'");
    }

    @Override
    public void atualizar(long id, Matricula f) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public List<Matricula> pesquisarPorDia(LocalDate dataMatricula) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pesquisarPorDia'");
    }

}
