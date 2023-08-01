package com.viit0r.cursospring;

import com.viit0r.cursospring.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();

    @RequestMapping(value = "/soma/{numeroUm}/{numeroDois}", method = RequestMethod.GET)
    public Double soma(
            @PathVariable(value = "numeroUm") String numeroUm,
            @PathVariable(value = "numeroDois") String numeroDois) throws Exception {
        if(!isNumerico(numeroUm) || !isNumerico(numeroDois)) {
            throw new UnsupportedMathOperationException("Por favor, digite um valor numérico");
        }
        return convertToDouble(numeroUm) + convertToDouble(numeroDois);
    }

    private Double convertToDouble(String numero) {
        if(numero == null) return 0D;

        String numeroFormatado = numero.replaceAll(",",".");

        if(isNumerico(numeroFormatado)) return Double.parseDouble(numeroFormatado);

        return 0D;
    }

    private boolean isNumerico(String numero) {
        if(numero == null) return false;

        String numeroFormatado = numero.replaceAll(",",".");

        return numeroFormatado.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
