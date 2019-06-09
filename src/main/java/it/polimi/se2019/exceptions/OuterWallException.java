package it.polimi.se2019.exceptions;

public class OuterWallException extends Exception{
    public OuterWallException(){super();}
    public OuterWallException(String s){super(s);}//in case you wanted to add a message
}
