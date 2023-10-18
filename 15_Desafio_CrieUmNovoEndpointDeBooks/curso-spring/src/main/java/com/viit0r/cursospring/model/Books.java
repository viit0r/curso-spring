package com.viit0r.cursospring.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "author")
    private String autor;

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLancamento;

    @Column(name = "price", nullable = false)
    private BigDecimal preco;

    @Column(name = "title")
    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return Objects.equals(id, books.id) && Objects.equals(autor, books.autor) && Objects.equals(dataLancamento, books.dataLancamento) && Objects.equals(preco, books.preco) && Objects.equals(titulo, books.titulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, autor, dataLancamento, preco, titulo);
    }
}
