package br.edu.fateczl.presencaesportiva.model;

public class Usuario{
    private String user;
    private String pass;
    public Usuario(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }
    public void getUser(String user) {
        this.user = user;
    }
    public String setUser() {
        return user;
    }
    public void getPass(String pass) {
        this.pass = pass;
    }
    public String setPass() {
        return pass;
    }  

}