package com.bam.seabattle_v1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bam.seabattle_v1.logic.Point;
import com.bam.seabattle_v1.logic.Ship;
import com.bam.seabattle_v1.logic.ShipCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {

        ShipCreator _shipFactory = new ShipCreator();
        List<Point> all = new ArrayList<>();
        List<Ship> _ships = _shipFactory.generate(4,1);
        List<Ship> _ships1 = _shipFactory.generate(3,2);
        List<Ship> _ships2 = _shipFactory.generate(2,3);
        List<Ship> _ships3 = _shipFactory.generate(1,4);

        _ships.forEach(i -> {
            all.addAll(i.getPoints());
        });
        _ships1.forEach(i -> {
            all.addAll(i.getPoints());
        });

        _ships2.forEach(i -> {
            all.addAll(i.getPoints());
        });

        _ships3.forEach(i -> {
            all.addAll(i.getPoints());
        });

        Map<Point, Long> map = all.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        boolean result = true;
        for (Map.Entry<Point, Long> entry : map.entrySet()) {
            Long v = entry.getValue();
            if (v != 1) {
                result = false;
                break;
            }
        }
        assertEquals(true, result);
    }

    @Test
    public void charTest() {
        char a = 'a';
        a += 3;
        assertEquals(2, 5-3);
    }
}