package it.polimi.se2019.test_controller;

import it.polimi.se2019.controller.PlayerManager;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayerManager {

    @Test
    public void testListOffenders()throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Method method = PlayerManager.class.getDeclaredMethod("listOffenders", char[].class);
        method.setAccessible(true);

        //3 R, 3 B, 2 G, 2 W, 1 Y, 1 V
        char [] damageTrack={'r','b','b','b','g','w','g','w','y','r','r','v'};

        StringBuilder stringBuilder=new StringBuilder("[");
        char [] returnMethod= (char[])method.invoke(null,damageTrack);
        for(char car:returnMethod){
            stringBuilder.append(car);
            stringBuilder.append(", ");
        }
        stringBuilder.replace(stringBuilder.lastIndexOf(", "),stringBuilder.length(),"]");

        assertEquals(stringBuilder.toString(),"[r, b, g, w, y, v]");
    }

}
