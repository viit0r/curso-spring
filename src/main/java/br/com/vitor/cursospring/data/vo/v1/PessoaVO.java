package br.com.vitor.cursospring.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;
import java.io.Serializable;

@JsonPropertyOrder({"id", "nome", "sobrenome", "endereco", "genero"})
public class PessoaVO extends RepresentationModel<PessoaVO> implements Serializable {

    public PessoaVO() {}

    @JsonProperty("id")
    @Mapping("id")
    private Long key;
    private String nome;
    private String sobrenome;
    private String endereco;
    private String genero;

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

        if (!key.equals(pessoa.key)) return false;
        if (!nome.equals(pessoa.nome)) return false;
        if (!sobrenome.equals(pessoa.sobrenome)) return false;
        if (!endereco.equals(pessoa.endereco)) return false;
        return genero.equals(pessoa.genero);
    }

    @Override
    public int hashCode() {
        int result = key.hashCode();
        result = 31 * result + nome.hashCode();
        result = 31 * result + sobrenome.hashCode();
        result = 31 * result + endereco.hashCode();
        result = 31 * result + genero.hashCode();
        return result;
    }
}
