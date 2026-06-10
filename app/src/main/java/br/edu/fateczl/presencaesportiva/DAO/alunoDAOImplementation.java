package br.edu.fateczl.presencaesportiva.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.presencaesportiva.model.Aluno;

public class AlunoDAOImplementation implements AlunoDAO{

    private static final String DB_URI = 
        "jdbc:mariadb://localhost:3306/presenca_esportiva?allowPublicKeyRetrieval=true&useSSL=false&createDatabaseIfNotExist=true";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "123456";

    private Connection con;

    public AlunoDAOImplementation() {
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
            String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, "%" + nome + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                a.setEmail(rs.getString("email"));
                a.setTelefone(rs.getString("telefone"));
                a.setDataNascimento(rs.getDate("nascimento").toLocalDate());
                a.setEndereco(rs.getString("endereco"));
                a.setModalidade(rs.getString("modalidade"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar aluno");
            e.printStackTrace();
        }
        return lista;
    }
}
