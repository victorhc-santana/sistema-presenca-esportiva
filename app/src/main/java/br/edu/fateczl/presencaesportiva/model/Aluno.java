package br.edu.fateczl.presencaesportiva.model;

import java.time.LocalDate;

public class Aluno {
    private int id = 0;
    private String nome = "";
    private String cpf = "";
    private String email = "";
    private String telefone = "";
    private LocalDate dataNascimento = LocalDate.now();
    private String endereco = "";
    private String modalidade = "";

    public Aluno() {
        super();
    }

    public Aluno(int id, String nome, String cpf, String email, String telefone,
         LocalDate dataNascimento, String endereco, String modalidade) {
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


    public String getCpf() {
        return cpf;
    }


    public void setCpf(String cpf) {
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


    public LocalDate getDataNascimento() {
        return dataNascimento;
    }


    public void setDataNascimento(LocalDate dataNascimento) {
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
