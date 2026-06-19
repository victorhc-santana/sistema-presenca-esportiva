package br.edu.fateczl.presencaesportiva.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import br.edu.fateczl.presencaesportiva.model.*;

public class Mapper {

    public static Aluno mapearAluno(ResultSet rs) throws SQLException {
        Aluno aluno = new Aluno();
        aluno.setId(rs.getLong("aluno_id"));
        aluno.setNome(rs.getString("nome"));
        aluno.setCpf(rs.getString("cpf"));
        aluno.setEmail(rs.getString("email"));
        aluno.setTelefone(rs.getString("telefone"));
        aluno.setDataNascimento(rs.getObject("nascimento", LocalDate.class));
        aluno.setEndereco(rs.getString("endereco"));
        aluno.setModalidade(rs.getString("modalidade"));
        return aluno;
    }

    public static Turma mapearTurma(ResultSet rs) throws SQLException {
        Turma turma = new Turma();
        turma.setId(rs.getInt("turma_id"));
        turma.setNome(rs.getString("turma_nome"));
        turma.setModalidade(rs.getString("turma_modalidade"));
        turma.setProfessor(rs.getString("professor"));
        turma.setHorario(rs.getString("horario"));
        turma.setDiaSemana(rs.getString("dia_semana"));
        turma.setVagasTotal(rs.getInt("vagas_total"));
        turma.setNivel(rs.getString("nivel"));
        return turma;
    }

    public static Matricula mapearMatricula(ResultSet rs) throws SQLException {
        Matricula matricula = new Matricula();
        matricula.setId(rs.getLong("matricula_id"));
        matricula.setAluno(mapearAluno(rs));
        matricula.setTurma(mapearTurma(rs));
        matricula.setDataMatricula(rs.getObject("data_matricula", LocalDate.class));
        return matricula;
    }

    public static Presenca mapearPresenca(ResultSet rs) throws SQLException {
        Presenca presenca = new Presenca();
        presenca.setId(rs.getLong("id"));
        presenca.setMatricula(mapearMatricula(rs));
        presenca.setDia(rs.getObject("dia", LocalDate.class));
        presenca.setStatus(rs.getBoolean("status"));
        return presenca;
    }
}
