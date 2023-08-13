package com.viit0r.cursospring.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

// Define a ordem da exibição do JSON
@JsonPropertyOrder({"id", "primeiro_nome", "ultimo_nome", "endereco", "genero"})
public class PersonDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @JsonProperty("primeiro_nome") //Define o nome que este campo terá no JSON
    private String primeiroNome;

    @JsonProperty("ultimo_nome") //Define o nome que este campo terá no JSON
    private String ultimoNome;
    private String endereco;

    @JsonIgnore //Ignora este campo no JSON
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
}
