package br.edu.fateczl.presencaesportiva.model;

import java.sql.Time;

public class Turma {
    private int id;
    private String nome;
    private String modalidade;
    private String professor;
    private Time horario;
    private String diaSemana;
    private int vagasTotal;
    private String nivel;

    public Turma(int id, String nome, String modalidade, String professor, Time horario, String diaSemana, int vagasTotal, String nivel) {
        this.id = id;
        this.nome = nome;
        this.modalidade = modalidade;
        this.professor = professor;
        this.horario = horario;
        this.diaSemana = diaSemana;
        this.vagasTotal = vagasTotal;
        this.nivel = nivel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getVagasTotal() {
        return vagasTotal;
    }

    public void setVagasTotal(int vagasTotal) {
        this.vagasTotal = vagasTotal;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }


}
