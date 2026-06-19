package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Turma;
import br.edu.fateczl.presencaesportiva.util.ConexaoDB;
import br.edu.fateczl.presencaesportiva.util.Mapper;

public class TurmaDAOImplementation implements TurmaDAO {

    private final Connection con;

    public TurmaDAOImplementation() throws SQLException {
        this.con = ConexaoDB.getConexao();
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
            String sqlPresenca = "DELETE FROM presenca WHERE matricula_id IN (SELECT id FROM matricula WHERE turma_id = ?)";
            var stmtPresenca = con.prepareStatement(sqlPresenca);
            stmtPresenca.setLong(1, a.getId());
            stmtPresenca.executeUpdate();

            String sqlMatricula = "DELETE FROM matricula WHERE turma_id = ?";
            var stmtMatricula = con.prepareStatement(sqlMatricula);
            stmtMatricula.setLong(1, a.getId());
            stmtMatricula.executeUpdate();
            
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
    public void atualizar(long id, Turma a) {
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
            stm.setLong(8, id);
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
            String sql = "SELECT id AS turma_id FROM turma WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lista.add(Mapper.mapearTurma(rs));
            }
            System.out.println("turmas selecionadas com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar turma");
            e.printStackTrace();
        }
        return lista;

    }
}