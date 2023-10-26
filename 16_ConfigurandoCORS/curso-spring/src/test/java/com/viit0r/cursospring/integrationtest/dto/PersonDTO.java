package com.viit0r.cursospring.integrationtest.dto;

import java.io.Serializable;
import java.util.Objects;

public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String primeiroNome;
    private String ultimoNome;
    private String endereco;
    private String genero;

    public PersonDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(id, personDTO.id) && Objects.equals(primeiroNome, personDTO.primeiroNome) && Objects.equals(ultimoNome, personDTO.ultimoNome) && Objects.equals(endereco, personDTO.endereco) && Objects.equals(genero, personDTO.genero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, primeiroNome, ultimoNome, endereco, genero);
    }
}
