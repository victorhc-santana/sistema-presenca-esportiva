package br.edu.fateczl.presencaesportiva.model;

import java.time.LocalDate;

public class Presenca {
    private long id;
    private long alunoId;
    private long turmaId;
    private String turmaNome;
    private LocalDate dia;
    private boolean status;

    public Presenca(long id, long alunoId, long turmaId, String turmaNome, LocalDate dia, boolean status) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.turmaNome = turmaNome;
        this.dia = dia;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    public long getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(long turmaId) {
        this.turmaId = turmaId;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTurmaNome() {
        return turmaNome;
    }

    public void setTurmaNome(String turmaNome) {
        this.turmaNome = turmaNome;
    }

}
