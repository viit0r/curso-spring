package com.viit0r.cursospring;

public class Saudacao {

    private final Long id;
    private final String conteudo;

    public Saudacao(Long id, String conteudo) {
        this.id = id;
        this.conteudo = conteudo;
    }

    public Long getId() {
        return id;
    }

    public String getConteudo() {
        return conteudo;
    }
}
