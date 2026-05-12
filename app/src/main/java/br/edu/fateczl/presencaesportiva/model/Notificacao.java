package br.edu.fateczl.presencaesportiva.model;

public class Notificacao {
    private int id;
    private int alunoId;
    private String mensagem;
    private String data;
    private String tipoNotificacao;
    private boolean enviado;
    private String canal;

    public Notificacao(int id, int alunoId, String mensagem, String data, String tipoNotificacao, boolean enviado, String canal) {
        this.id = id;
        this.alunoId = alunoId;
        this.mensagem = mensagem;
        this.data = data;
        this.tipoNotificacao = tipoNotificacao;
        this.enviado = enviado;
        this.canal = canal;
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipoNotificacao() {
        return tipoNotificacao;
    }

    public void setTipoNotificacao(String tipoNotificacao) {
        this.tipoNotificacao = tipoNotificacao;
    }

    public boolean isEnviado() {
        return enviado;
    }

    public void setEnviado(boolean enviado) {
        this.enviado = enviado;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }
    
}
