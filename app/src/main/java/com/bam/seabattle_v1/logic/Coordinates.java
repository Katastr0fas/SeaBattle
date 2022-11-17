package com.bam.seabattle_v1.logic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Coordinates {

    private final List<Point> points = new ArrayList<>();

    public Coordinates() {
        for (char i = 'a'; i <= 'j'; i++) {
            for (int j = 1; j <= 10; j++) {
                points.add(new Point(i, j));
            }
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void remove(@NonNull List<Point> removePoints) {
        removePoints.forEach(points::remove);
    }


}
