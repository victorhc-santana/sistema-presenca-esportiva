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
import br.edu.fateczl.presencaesportiva.model.Turma;

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
public List<Matricula> pesquisarPorAluno(String nomeAluno) {
    List<Matricula> matriculas = new ArrayList<>();
    try {
        String sql = """
            SELECT m.id, m.data_matricula,
                   a.id AS aluno_id, a.nome, a.cpf, a.email, a.telefone, a.nascimento, a.endereco, a.modalidade,
                   t.id AS turma_id, t.nome AS turma_nome, t.modalidade AS turma_modalidade,
                   t.professor, t.horario, t.dia_semana, t.vagas_total, t.nivel
            FROM matricula m
            JOIN aluno a ON m.aluno_id = a.id
            JOIN turma t ON m.turma_id = t.id
            WHERE a.nome LIKE ?
            """;
        var stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nomeAluno + "%");
        var rs = stmt.executeQuery();

        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setId(rs.getLong("aluno_id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setEmail(rs.getString("email"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setDataNascimento(rs.getObject("nascimento", LocalDate.class));
            aluno.setEndereco(rs.getString("endereco"));
            aluno.setModalidade(rs.getString("modalidade"));

            Turma turma = new Turma();
            turma.setId(rs.getInt("turma_id"));
            turma.setNome(rs.getString("turma_nome"));
            turma.setModalidade(rs.getString("turma_modalidade"));
            turma.setProfessor(rs.getString("professor"));
            turma.setHorario(rs.getString("horario"));
            turma.setDiaSemana(rs.getString("dia_semana"));
            turma.setVagasTotal(rs.getInt("vagas_total"));
            turma.setNivel(rs.getString("nivel"));

            Matricula matricula = new Matricula();
            matricula.setId(rs.getLong("id"));
            matricula.setAluno(aluno);
            matricula.setTurma(turma);
            matricula.setDataMatricula(rs.getObject("data_matricula", LocalDate.class));

            matriculas.add(matricula);
        }

    } catch (SQLException e) {
        System.out.println("Erro ao pesquisar matricula por aluno");
        e.printStackTrace();
    }
    return matriculas;
    }

    @Override
    public List<Matricula> pesquisarPorTurma(String nomeTurma) {
    List<Matricula> matriculas = new ArrayList<>();
    try {
        String sql = """
            SELECT m.id, m.data_matricula,
                   a.id AS aluno_id, a.nome, a.cpf, a.email, a.telefone, a.nascimento, a.endereco, a.modalidade,
                   t.id AS turma_id, t.nome AS turma_nome, t.modalidade AS turma_modalidade,
                   t.professor, t.horario, t.dia_semana, t.vagas_total, t.nivel
            FROM matricula m
            JOIN aluno a ON m.aluno_id = a.id
            JOIN turma t ON m.turma_id = t.id
            WHERE t.nome LIKE ?
            """;
        var stmt = con.prepareStatement(sql);
        stmt.setString(1, "%" + nomeTurma + "%");
        var rs = stmt.executeQuery();

        while (rs.next()) {
            Aluno aluno = new Aluno();
            aluno.setId(rs.getLong("aluno_id"));
            aluno.setNome(rs.getString("nome"));
            aluno.setCpf(rs.getString("cpf"));
            aluno.setEmail(rs.getString("email"));
            aluno.setTelefone(rs.getString("telefone"));
            aluno.setDataNascimento(rs.getObject("nascimento", LocalDate.class));
            aluno.setEndereco(rs.getString("endereco"));
            aluno.setModalidade(rs.getString("modalidade"));

            Turma turma = new Turma();
            turma.setId(rs.getInt("turma_id"));
            turma.setNome(rs.getString("turma_nome"));
            turma.setModalidade(rs.getString("turma_modalidade"));
            turma.setProfessor(rs.getString("professor"));
            turma.setHorario(rs.getString("horario"));
            turma.setDiaSemana(rs.getString("dia_semana"));
            turma.setVagasTotal(rs.getInt("vagas_total"));
            turma.setNivel(rs.getString("nivel"));

            Matricula matricula = new Matricula();
            matricula.setId(rs.getLong("id"));
            matricula.setAluno(aluno);
            matricula.setTurma(turma);
            matricula.setDataMatricula(rs.getObject("data_matricula", LocalDate.class));

            matriculas.add(matricula);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao pesquisar matricula por turma");
        e.printStackTrace();
    }
    return matriculas;
}   
}


