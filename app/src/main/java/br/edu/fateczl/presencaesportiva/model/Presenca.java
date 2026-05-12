package br.edu.fateczl.presencaesportiva.model;

public class Presenca {
    private int id;
    private int alunoId;
    private int turmaId;
    private String data;
    private String status;
    //private string observacao; para adicionar mais atributos

    public Presenca(int id, int alunoId, int turmaId, String data, String status) {
        this.id = id;
        this.alunoId = alunoId;
        this.turmaId = turmaId;
        this.data = data;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
