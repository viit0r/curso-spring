package com.viit0r.cursospring.service;

import com.viit0r.cursospring.exception.UnsupportedMathOperationException;
import com.viit0r.cursospring.util.NumberConverter;
import com.viit0r.cursospring.util.SimpleMath;
import org.springframework.stereotype.Service;

@Service
public class MathService {

    private final SimpleMath math = new SimpleMath();

    public Double soma(String numeroUm, String numeroDois) {
        if(!NumberConverter.isNumerico(numeroUm) || !NumberConverter.isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.somar(NumberConverter.convertToDouble(numeroUm), NumberConverter.convertToDouble(numeroDois));
    }

    public Double subtrai(String numeroUm, String numeroDois) {
        if(!NumberConverter.isNumerico(numeroUm) || !NumberConverter.isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.subtrair(NumberConverter.convertToDouble(numeroUm), NumberConverter.convertToDouble(numeroDois));
    }

    public Double multiplica(String numeroUm, String numeroDois) {
        if(!NumberConverter.isNumerico(numeroUm) || !NumberConverter.isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.multiplicar(NumberConverter.convertToDouble(numeroUm), NumberConverter.convertToDouble(numeroDois));
    }

    public Double divide(String numeroUm, String numeroDois) {
        if(!NumberConverter.isNumerico(numeroUm) || !NumberConverter.isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.dividir(NumberConverter.convertToDouble(numeroUm), NumberConverter.convertToDouble(numeroDois));
    }

    public Double calculaMedia(String numeroUm, String numeroDois) {
        if(!NumberConverter.isNumerico(numeroUm) || !NumberConverter.isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.media(NumberConverter.convertToDouble(numeroUm), NumberConverter.convertToDouble(numeroDois));
    }

    public Double calculaRaizQuadrada(String numero) {
        if(!NumberConverter.isNumerico(numero)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return math.raizQuadrada(NumberConverter.convertToDouble(numero));
    }
}
