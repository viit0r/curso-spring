package br.com.vitor.cursospring.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "pessoa")
public class Pessoa implements Serializable {

    public Pessoa() {}

    @Id
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 80)
    private String sobrenome;

    @Column(nullable = false, length = 100)
    private String endereco;

    @Column(nullable = false, length = 9)
    private String genero;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pessoa pessoa = (Pessoa) obj;

        if (!id.equals(pessoa.id)) return false;
        if (!nome.equals(pessoa.nome)) return false;
        if (!sobrenome.equals(pessoa.sobrenome)) return false;
        if (!endereco.equals(pessoa.endereco)) return false;
        return genero.equals(pessoa.genero);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + sobrenome.hashCode();
        result = 31 * result + endereco.hashCode();
        result = 31 * result + genero.hashCode();
        return result;
    }
}
