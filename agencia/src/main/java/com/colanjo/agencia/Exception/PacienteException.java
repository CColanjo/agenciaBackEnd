package com.colanjo.agencia.Exception;

public class PacienteException extends RuntimeException {

    public PacienteException(Integer id) {
        super("Could not find employee " + id);
    }
}
