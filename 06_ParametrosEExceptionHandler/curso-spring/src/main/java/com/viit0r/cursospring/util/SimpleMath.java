package com.viit0r.cursospring.util;

public class SimpleMath {
    public Double somar(Double numeroUm, Double numeroDois) {
        return numeroUm + numeroDois;
    }

    public Double subtrair(Double numeroUm, Double numeroDois) {
        return numeroUm - numeroDois;
    }

    public Double multiplicar(Double numeroUm, Double numeroDois) {
        return numeroUm * numeroDois;
    }

    public Double dividir(Double numeroUm, Double numeroDois) {
        return numeroUm / numeroDois;
    }

    public Double media(Double numeroUm, Double numeroDois) {
        return (numeroUm + numeroDois) / 2;
    }

    public Double raizQuadrada(Double numero) {
        return Math.sqrt(numero);
    }
}
