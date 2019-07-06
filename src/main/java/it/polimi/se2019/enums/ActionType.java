package it.polimi.se2019.enums;

import java.io.Serializable;

public enum ActionType implements Serializable {
    NORMAL1, //(move, move, move)
    NORMAL2, //(move,(move) grab)
    NORMAL3, //((move), shoot)

    //x2
    FRENZY1, //(move, reload, shoot)
    FRENZY2, //(move, move, move, move)
    FRENZY3, //(move, move, grab)

    //x1
    FRENZY4, //(move, move, reload, shoot)
    FRENZY5 //(move, move, move, grab)
}