package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Turma;

public class turmaDAOImplementation implements turmaDAO {

    private static final String DB_URI = "jdbc:mariadb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public turmaDAOImplementation() {
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
    public void cadastrar(Turma a) {
        try {
            String sql = "INSERT INTO turma (nome, modalidade, professor, horario, dia_semana, vagas_total, nivel) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getModalidade());
            stmt.setString(3, a.getProfessor());
            stmt.setString(4, a.getHorario());
            stmt.setString(5, a.getDiaSemana());
            stmt.setInt(6, a.getVagasTotal());
            stmt.setString(7, a.getNivel());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar turma");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Turma a) {
        try {
            String sql = "DELETE FROM turma WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar turma");
            e.printStackTrace();
        }
    }

    @Override
    public void atualizar(int id, Turma a) {
        try {
            String sql = "UPDATE turma SET nome=?, modalidade=?, professor=?, horario=?, " +
                    "dia_semana=?, vagas_total=?, nivel=? WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, a.getNome());
            stm.setString(2, a.getModalidade());
            stm.setString(3, a.getProfessor());
            stm.setString(4, a.getHorario());
            stm.setString(5, a.getDiaSemana());
            stm.setInt(6, a.getVagasTotal());
            stm.setString(7, a.getNivel());
            stm.setInt(8, id);
            stm.executeUpdate();
            System.out.println("Turma atualizada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar turma");
            e.printStackTrace();

        }
    }

    @Override
    public List<Turma> pesquisarPorNome(String nome) {
        List<Turma> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM turma WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Turma a = new Turma();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setModalidade(rs.getString("modalidade"));
                a.setProfessor(rs.getString("professor"));
                a.setHorario(rs.getString("horario"));
                a.setDiaSemana(rs.getString("dia_semana"));
                a.setVagasTotal(rs.getInt("vagas_total"));
                a.setNivel(rs.getString("nivel"));
                lista.add(a);
            }
            System.out.println("turmas selecionadas com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar turma");
            e.printStackTrace();
        }
        return lista;

    }
}