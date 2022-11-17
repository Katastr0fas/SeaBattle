package com.bam.seabattle_v1.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnemyLogic {
    private final Coordinates __coordinates = new Coordinates();
    private final Random __random = new Random();

    public Point getPoint(){
        int index = __random.nextInt() % __coordinates.getPoints().size();
        Point _point = __coordinates.getPoints().get(Math.abs(index));
        __coordinates.getPoints().remove(_point);
        return  _point;
    }
}
