package com.carlosribeiro;
//Classe de exceção

//Exception é esperada
//RunTimeException é inesperada
public class DataInvalidaException extends Exception{

    public DataInvalidaException (String message) {
        super(message);
        //this.message = message;
    }
}
