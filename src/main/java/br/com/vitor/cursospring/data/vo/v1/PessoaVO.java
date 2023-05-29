package br.com.vitor.cursospring.data.vo.v1;

import java.io.Serializable;

public class PessoaVO implements Serializable {

    public PessoaVO() {}

    private Long id;

    private String nome;

    private String sobrenome;

    private String endereco;

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

        PessoaVO pessoa = (PessoaVO) obj;

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
