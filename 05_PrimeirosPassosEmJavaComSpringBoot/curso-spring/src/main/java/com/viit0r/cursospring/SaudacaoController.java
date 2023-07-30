package com.viit0r.cursospring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SaudacaoController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping("/saudar")
    public Saudacao saudar(@RequestParam(value = "nome", defaultValue = "World") String nome) {
        return new Saudacao(counter.incrementAndGet(), String.format(template, nome));
    }
}
