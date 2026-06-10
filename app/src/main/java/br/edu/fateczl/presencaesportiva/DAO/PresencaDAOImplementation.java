package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Matricula;
import br.edu.fateczl.presencaesportiva.model.Presenca;

public class PresencaDAOImplementation implements PresencaDAO {
    private static final String DB_URI = "jdbc:maradb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public PresencaDAOImplementation() {
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
    public void cadastrar(Presenca p) {
        try{
            String sql = "INSERT INTO presenca (id, matricula, dia, status) VALUES (?, ?, ?, ?)";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.setObject(2, p.getMatricula());
            stmt.setDate(3, java.sql.Date.valueOf(p.getDia()));
            stmt.setBoolean(4, p.getStatus());
            stmt.executeUpdate();
         } catch (SQLException e) {
             System.out.println("Erro ao cadastrar presença");
             e.printStackTrace();
        }
    }

    @Override
    public void apagar(Presenca p) {
        try {
            String sql = "DELETE FROM presenca WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar presença");
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(long id, Presenca p) {
        try {
            String sql = "UPDATE presenca SET matricula = ?, dia = ?, status = ? WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setObject(1, p.getMatricula());
            stmt.setDate(2, java.sql.Date.valueOf(p.getDia()));
            stmt.setBoolean(3, p.getStatus());
            stmt.setLong(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar presença");
            e.printStackTrace();
        }
    }

    @Override
    public List<Presenca> pesquisarPorDia(String modalidade, LocalDate dia) {
        List<Presenca> presencas = new ArrayList<>();
        try {
            String sql = "SELECT p.id, p.matricula, p.dia, p.status " +
                         "FROM presenca p " +
                         "WHERE p.dia = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(dia));
            var rs = stmt.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                Matricula matricula = (Matricula) rs.getObject("matricula");
                LocalDate data = rs.getDate("dia").toLocalDate();
                boolean status = rs.getBoolean("status");
                presencas.add(new Presenca(id,  matricula, data, status));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar presença por dia");
            e.printStackTrace();
        }
        return presencas;
    }
}