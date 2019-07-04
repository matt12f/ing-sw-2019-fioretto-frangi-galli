package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.PlayerManager;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerManager {

    @Test
    public void testListOffenders()throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = PlayerManager.class.getDeclaredMethod("listOffenders", char[].class);
        method.setAccessible(true);

        //3 R, 3 B, 2 G, 2 W, 1 Y, 1 V
        char [] damageTrack={'r','b','b','b','g','w','g','w','y','r','r','v'};

        ArrayList<Character> returnMethod= (ArrayList<Character>)method.invoke(null,damageTrack);

        assertEquals("[r, b, g, w, y, v]", returnMethod.toString());

        char [] damageTrack3={'r','b','b','b','g','w','w','w','y','b','r','v'};

        returnMethod= (ArrayList<Character>)method.invoke(null,damageTrack3);

        assertEquals("[b, w, r, g, y, v]", returnMethod.toString());

        char [] damageTrack2={'r','b','b','b','g','w','g','w',' ',' ',' ',' '};

        returnMethod= (ArrayList<Character>)method.invoke(null,damageTrack2);

        assertEquals("[ , b, g, w, r]", returnMethod.toString());

        returnMethod.removeIf(character -> character.equals(' '));

        assertEquals("[b, g, w, r]",returnMethod.toString());

    }


}
