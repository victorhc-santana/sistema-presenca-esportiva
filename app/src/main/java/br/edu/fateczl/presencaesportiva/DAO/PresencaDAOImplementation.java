package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Presenca;
import br.edu.fateczl.presencaesportiva.util.ConexaoDB;
import br.edu.fateczl.presencaesportiva.util.Mapper;

public class PresencaDAOImplementation implements PresencaDAO {

    private final Connection con;

    public PresencaDAOImplementation() throws SQLException {
        this.con = ConexaoDB.getConexao();
    }
    @Override
    public void cadastrar(Presenca p) {
        try{
            String sql = "INSERT INTO presenca (id, matricula_id, dia, status) VALUES (?, ?, ?, ?)";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getId());
            stmt.setLong(2, p.getMatricula().getId());
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
            String sql = "UPDATE presenca SET matricula_id = ?, dia = ?, status = ? WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, p.getMatricula().getId());
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
    public Presenca buscarPorMatriculaEDia(long matriculaId, LocalDate dia) {
        List<Presenca> presencas = new ArrayList<>();
        try {
            String sql = """
                SELECT p.id, p.dia, p.status,
                    m.id AS matricula_id, m.data_matricula,
                    a.id AS aluno_id, a.nome, a.cpf, a.email, a.telefone, a.nascimento, a.endereco, a.modalidade,
                    t.id AS turma_id, t.nome AS turma_nome, t.modalidade AS turma_modalidade,
                    t.professor, t.horario, t.dia_semana, t.vagas_total, t.nivel
                FROM presenca p
                JOIN matricula m ON p.matricula_id = m.id
                JOIN aluno a ON m.aluno_id = a.id
                JOIN turma t ON m.turma_id = t.id
                WHERE m.id = ? AND p.dia = ?
                """;
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, matriculaId);
            stmt.setObject(2, dia);
            var rs = stmt.executeQuery();
            if (rs.next()) {  
                presencas.add(Mapper.mapearPresenca(rs));
                return Mapper.mapearPresenca(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // null = ainda não existe no banco
    }
   @Override
   public List<Presenca> pesquisarPorDia(String nomeTurma, LocalDate dia) {
    List<Presenca> presencas = new ArrayList<>();
    try {
        String sql = """
            SELECT p.id, p.dia, p.status,
                   m.id AS matricula_id, m.data_matricula,
                   a.id AS aluno_id, a.nome, a.cpf, a.email, a.telefone, a.nascimento, a.endereco, a.modalidade,
                   t.id AS turma_id, t.nome AS turma_nome, t.modalidade AS turma_modalidade,
                   t.professor, t.horario, t.dia_semana, t.vagas_total, t.nivel
            FROM presenca p
            JOIN matricula m ON p.matricula_id = m.id
            JOIN aluno a ON m.aluno_id = a.id
            JOIN turma t ON m.turma_id = t.id
            WHERE t.nome LIKE ? AND p.dia = ?
            """;
        var stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nomeTurma + "%");
        stmt.setObject(2, dia);
        var rs = stmt.executeQuery();

        while (rs.next()) {          
            presencas.add(Mapper.mapearPresenca(rs));
        }
    } catch (SQLException e) {
        System.out.println("Erro ao pesquisar presença por dia");
        e.printStackTrace();
    }
    return presencas;
   }
}
