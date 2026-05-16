package br.edu.fateczl.presencaesportiva.model;

public class AcessProf extends Usuario {
    private int acessLevel;
    public AcessProf(String user, String pass) {
        super(user, pass);
        this.acessLevel = 2; // Nível de acesso para professor
    }
    public int getAcessLevel() {
        return acessLevel;
    }
    //necessario?
    public void setAcessLevel(int acessLevel) {
        this.acessLevel = acessLevel;
    }

}
