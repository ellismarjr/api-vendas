package br.com.grownext.excepetion;

public class SenhaInvalidaException extends Throwable {
    public SenhaInvalidaException() {
        super("Senha invalida.");
    }
}
