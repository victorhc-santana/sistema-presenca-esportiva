package br.edu.fateczl.presencaesportiva.model;

import java.time.LocalDate;

public class Presenca {
    private long id;
    private Matricula matricula;
    private LocalDate dia;
    private boolean status;

    public Presenca(long id, Matricula matricula, LocalDate dia, boolean status) {
        this.id = id;

        this.dia = dia;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Matricula getMatricula() {
        return matricula;
    }
    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
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

}
