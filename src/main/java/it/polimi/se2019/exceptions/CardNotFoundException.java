package it.polimi.se2019.exceptions;

public class CardNotFoundException extends Exception{
    public CardNotFoundException(){super();}
    public CardNotFoundException(String s){super(s);} //in case you wanted to add a message

}
