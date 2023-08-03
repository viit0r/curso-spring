package com.viit0r.cursospring.util;

public class NumberConverter {
    public static Double convertToDouble(String numero) {
        if(numero == null) return 0D;

        String numeroFormatado = numero.replaceAll(",",".");

        if(isNumerico(numeroFormatado)) return Double.parseDouble(numeroFormatado);

        return 0D;
    }

    public static boolean isNumerico(String numero) {
        if(numero == null) return false;

        String numeroFormatado = numero.replaceAll(",",".");

        return numeroFormatado.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
