package br.com.vitor.cursospring.exception;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class ExceptionResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Date timestamp;
    private String mensagem;
    private String detalhes;

    public ExceptionResponse(Date timestamp, String mensagem, String detalhes) {
        this.timestamp = timestamp;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }
}
