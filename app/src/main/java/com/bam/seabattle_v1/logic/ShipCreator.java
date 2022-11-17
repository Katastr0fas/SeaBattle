package com.bam.seabattle_v1.logic;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class ShipCreator {

    private final Random random = new Random();
    private final Coordinates coordinates = new Coordinates();

    public List<Ship> generate(int parts, int count) {
        List<Ship> ships = createShips(parts);
        List<Ship> filtered = filterShips(count, ships);
        filtered.forEach(i ->
                coordinates.remove(getShipWithAroundPoints(i).getPoints()));
        return filtered;

    }

    @NonNull
    private List<Ship> filterShips(int count, List<Ship> ships) {
        List<Ship> result = new ArrayList<>();
        while (result.size() < count) {
            int index = random.nextInt() % ships.size();
            Ship ship = ships.get(Math.abs(index));
            Ship shipWithBorder = getShipWithAroundPoints(ship);
            ships.removeIf(shipWithBorder::isCross);
            result.add(ship);
        }

        return result;
    }

    @NonNull
    private Ship getShipWithAroundPoints(@NonNull Ship ship) {
        Set<Point> result = new HashSet<>();

        ship.getPoints().forEach(point -> {
            result.add(point.add(-1, -1));
            result.add(point.add(-1, 0));
            result.add(point.add(-1, 1));
            result.add(point.add(0, 1));
            result.add(point.add(1, 1));
            result.add(point.add(1, 0));
            result.add(point.add(1, -1));
            result.add(point.add(0, -1));
        });


        return new Ship(new ArrayList<>(result));

    }

    @NonNull
    private List<Ship> createShips(int parts) {
        List<Ship> ships = new ArrayList<>();
        coordinates.getPoints().forEach(i -> {

            Point endPointToX = i.add(parts, 0);
            Point endPointToY = i.add(0,parts);

            List<Point> pointsX = i.rangeX(endPointToX);
            List<Point> pointsY = i.rangeY(endPointToY);

            if (contain(pointsX)) {
                ships.add(new Ship(pointsX));
            }

            if (contain(pointsY)) {
                ships.add(new Ship(pointsY));
            }
        });
        return ships;
    }

    private boolean contain(List<Point> points) {
        for (Point p : points) {
            if (!coordinates.getPoints().contains(p)) return false;
        }
        return true;
    }

}
