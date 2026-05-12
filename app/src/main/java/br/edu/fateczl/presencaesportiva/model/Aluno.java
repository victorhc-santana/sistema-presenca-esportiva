package br.edu.fateczl.presencaesportiva.model;

import java.sql.Date;

public class Aluno {
    private int id;
    private String nome;
    private int cpf;
    private String email;
    private String telefone;
    private Date dataNascimento;
    private String endereco;
    private String modalidade;

    
    public Aluno(int id, String nome, int cpf, String email, String telefone,
         Date dataNascimento, String endereco, String modalidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.modalidade = modalidade;
        this.endereco = endereco;
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


    public int getCpf() {
        return cpf;
    }


    public void setCpf(int cpf) {
        this.cpf = cpf;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getTelefone() {
        return telefone;
    }


    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


    public Date getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    public String getEndereco() {
        return endereco;
    }


    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }


    public String getModalidade() {
        return modalidade;
    }


    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

}
