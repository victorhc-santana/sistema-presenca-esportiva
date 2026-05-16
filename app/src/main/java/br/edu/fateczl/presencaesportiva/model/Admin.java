package br.edu.fateczl.presencaesportiva.model;

public class Admin extends Usuario {
    private int acessLevel;
    public Admin(String user, String pass) {
        super(user, pass);
        this.acessLevel = 1; // Nível de acesso para admin
    }
    public int getAcessLevel() {
        return acessLevel;
    }
    //necessario?
    public void setAcessLevel(int acessLevel) {
        this.acessLevel = acessLevel;
    }
}
