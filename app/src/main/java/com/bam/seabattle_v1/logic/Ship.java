package com.bam.seabattle_v1.logic;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ship {

    private List<Point> points;


    public boolean isCross(Ship ship){
        for (Point p: points){
            if (ship.points.contains(p))
                return true;
        }
        return false;
    }

    private void setPoints(List<Point> points){
        this.points = points;
    }


    public Ship(List<Point> points) {
        setPoints(points);
    }

    public List<Point> getPoints() {
        return points;
    }


}
