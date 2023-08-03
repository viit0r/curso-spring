package com.viit0r.cursospring.controller;

import com.viit0r.cursospring.exception.UnsupportedMathOperationException;
import com.viit0r.cursospring.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    @Autowired
    MathService mathService;

    @RequestMapping(value = "/soma/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double soma(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) {

        return mathService.soma(numeroUm, numeroDois);
    }

    @RequestMapping(value = "/subtrai/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double subtrai(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) {

        return mathService.subtrai(numeroUm, numeroDois);
    }

    @RequestMapping(value = "/multiplica/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double multiplica(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) {
        return mathService.multiplica(numeroUm, numeroDois);
    }

    @RequestMapping(value = "/divide/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double divide(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) {
        return mathService.divide(numeroUm, numeroDois);
    }

    @RequestMapping(value = "/media/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double media(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) {
        return mathService.calculaMedia(numeroUm, numeroDois);
    }

    @RequestMapping(value = "/raiz/{numero}", method = RequestMethod.GET)
    public Double tiraRaiz(
            @PathVariable(value = "numero") String numero) {
        return mathService.calculaRaizQuadrada(numero);
    }
}
