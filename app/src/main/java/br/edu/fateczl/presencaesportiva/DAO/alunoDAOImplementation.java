package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Aluno;
import br.edu.fateczl.presencaesportiva.util.ConexaoDB;
import br.edu.fateczl.presencaesportiva.util.Mapper;

public class AlunoDAOImplementation implements AlunoDAO{

    private final Connection con;

    public AlunoDAOImplementation() throws SQLException {
        this.con = ConexaoDB.getConexao();
    }

    @Override
    public void cadastrar(Aluno a) {
        try {
            String sql = "INSERT INTO aluno (nome, cpf, nascimento, email, telefone, modalidade, endereco) VALUES (?, ?, ?, ?, ?, ?, ?)";
            var stmt = con.prepareStatement(sql);
            stmt.setString(1, a.getNome());
            stmt.setString(2, a.getCpf());
            stmt.setDate(3, java.sql.Date.valueOf(a.getDataNascimento()));
            stmt.setString(4, a.getEmail());
            stmt.setString(5, a.getTelefone());
            stmt.setString(6, a.getModalidade());
            stmt.setString(7, a.getEndereco());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar aluno");
            e.printStackTrace();
        }
    }

    @Override
    public void apagar(Aluno a) {
        try {
            String sqlPresenca = "DELETE FROM presenca WHERE matricula_id IN (SELECT id FROM matricula WHERE aluno_id = ?)";
            var stmtPresenca = con.prepareStatement(sqlPresenca);
            stmtPresenca.setLong(1, a.getId());
            stmtPresenca.executeUpdate();

            String sqlMatricula = "DELETE FROM matricula WHERE aluno_id = ?";
            var stmtMatricula = con.prepareStatement(sqlMatricula);
            stmtMatricula.setLong(1, a.getId());
            stmtMatricula.executeUpdate();
            
            String sql = "DELETE FROM aluno WHERE id = ?";
            var stmt = con.prepareStatement(sql);
            stmt.setLong(1, a.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao apagar aluno");
            e.printStackTrace();
        } 
    }

    @Override
    public void atualizar(long id, Aluno a) {
        try {
            String sql = "UPDATE aluno SET nome=?, cpf=?, email=?, telefone=?, " +
                         "nascimento=?, endereco=?, modalidade=? WHERE id=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, a.getNome());
            stm.setString(2, a.getCpf());
            stm.setString(3, a.getEmail());
            stm.setString(4, a.getTelefone());
            stm.setDate(5, java.sql.Date.valueOf(a.getDataNascimento()));
            stm.setString(6, a.getEndereco());
            stm.setString(7, a.getModalidade());
            stm.setLong(8, id);
            stm.executeUpdate();
            System.out.println("Aluno atualizado com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno");
            e.printStackTrace();
        }
    }


    @Override
    public List<Aluno> pesquisarPorNome(String nome) {
        List<Aluno> lista = new ArrayList<>();
        try {
            String sql = "SELECT id AS aluno_id FROM aluno WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lista.add(Mapper.mapearAluno(rs));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar aluno");
            e.printStackTrace();
        }
        return lista;
    }
}
