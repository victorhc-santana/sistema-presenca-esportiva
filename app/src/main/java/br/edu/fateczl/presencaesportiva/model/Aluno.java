package br.edu.fateczl.presencaesportiva.model;

import java.sql.Date;

public class Aluno {
    int id;
    String nome;
    int cpf;
    String email;
    String telefone;
    Date dataNascimento;

    
    public Aluno(int id, String nome, int cpf, String email, String telefone, Date dataNascimento) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }
    
    public int getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public int getCpf() {
        return cpf;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    



}
