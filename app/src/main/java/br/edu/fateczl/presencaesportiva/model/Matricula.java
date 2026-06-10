package br.edu.fateczl.presencaesportiva.model;

import java.time.LocalDate;

public class Matricula {

    private long id = 0;
    private Aluno aluno;
    private Turma turma;
    private LocalDate dataMatricula;

    public Matricula() {
        super();
    }

    public Matricula(long id, Aluno aluno, Turma turma, LocalDate dataMatricula) {
        this.id = id;
        this.aluno = aluno;
        this.turma = turma;
        this.dataMatricula = dataMatricula;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

}
