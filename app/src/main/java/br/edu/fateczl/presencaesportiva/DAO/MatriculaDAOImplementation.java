package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
            String sql = "INSERT INTO matricula (aluno, turma, data_matricula) VALUES (?, ?, ?)";
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
        try {
            String sql = "DELETE FROM matricula WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, f.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar matricula");
            e.printStackTrace();
        }

    }

    @Override
    public void atualizar(long id, Matricula f) {
        try {
            String sql = "UPDATE matricula SET aluno=?, turma=?, data_matricula=? " +
             "WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, f.getAluno().getNome());
            stm.setString(2, f.getTurma().getNome());
            stm.setDate(3, java.sql.Date.valueOf(f.getDataMatricula()));
            stm.setLong(4, id);
            stm.executeUpdate();
            System.out.println("Matricula atualizada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar matricula");
            e.printStackTrace();
        }

    }

    @Override
    public List<Matricula> pesquisarPorAluno(Aluno nome) {
        List<Matricula> matriculas = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.aluno, p.turma, p.data_matricula " +
                    "FROM matricula p " +
                    "JOIN matricula m ON p.matricula_id = m.id " +
                    "WHERE m.modalidade = ? AND P.dia = ?";
            var stmt = con.prepareStatement(sql);
            //stmt.setString(1, turma);
            stmt.setDate(2, java.sql.Date.valueOf(dataMatricula));
            var rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                Matricula matricula = rs.getObject("matricula", Matricula.class);
                LocalDate data = rs.getDate("dia").toLocalDate();
                boolean status = rs.getBoolean("status");
                // corrigir para buscar a matrícula completa usando o matriculaId
                //matriculas.add(new Presenca(id, matricula, data, status));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar a matricula por aluno");
            e.printStackTrace();
        }
        return matriculas;
    }

}
