package com.bam.seabattle_v1.logic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Point {
    private char x;
    private int y;

    public Point(char x, int y) {
        this.x = x;
        this.y = y;
    }


    public Point add(int stepX, int stepY){
        Point _point = new Point(x, y);
        _point.x +=stepX;
        _point.y +=stepY;
        return _point;
    }



    public List<Point> rangeX(@NonNull Point end) {
        List<Point> points = new ArrayList<>();
        for (char i = x; i < end.getX(); i++) {
            points.add(new Point(i, y));

        }
        return points;
    }

    public List<Point> rangeY(@NonNull Point end) {
        List<Point> points = new ArrayList<>();
        for (int j = y; j < end.getY(); j++) {
            points.add(new Point(x, j));
        }
        return points;
    }


    public char getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @NonNull
    @Override
    public String toString() {
        return "" + x + y;
    }


    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Point __point = (Point) _o;
        return x == __point.x && y == __point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


}
