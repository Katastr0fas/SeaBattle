package com.bam.seabattle_v1.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bam.seabattle_v1.R;
import com.bam.seabattle_v1.logic.Coordinates;
import com.bam.seabattle_v1.logic.Point;
import com.bam.seabattle_v1.logic.Ship;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeaBattleFieldView extends LinearLayout {

    private boolean clickable = false;
    private final List<SeaBattleCellView> cells = new ArrayList<>();
    private List<Ship> ships = new ArrayList<>();
    private EndGameListener endGameListener;
    private EnemyStepListener enemyStepListener;

    private boolean miss = false;



    public SeaBattleFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
    }

    public void setShips(List<Ship> ships, boolean visible) {
        this.ships = ships;
        clear();
        initRows();
        initField();
        if (visible) {
            this.ships.forEach(s -> {
                s.getPoints().forEach(point -> {
                    changeCellBg(point, R.drawable.ship_part_bg);
                });
            });
        }
    }

    private void clear() {
        cells.clear();
        removeAllViews();
    }

    @Override
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }






    public void shot(Point point) {
        for (Ship ship : ships) {
            for (Point p : ship.getPoints()) {
                if (p.equals(point)) {
                    ship.getPoints().remove(p);
                    checkShipStatus(ship);
                    changeCellBg(p, R.drawable.enemy_ship_part_bg);
                    checkEndGame();
                    miss = false;
                    return;
                }

            }
        }
        changeCellBg(point, R.drawable.miss_shot_bg);
        miss = true;
    }


    private void checkShipStatus(@NonNull Ship ship) {
        if (clickable){
            if (ship.getPoints().size() != 0)
                showMessage("Подбит");
            else {
                ships.remove(ship);
                showMessage("Потоплен");
            }
        }
    }

    private void changeCellBg(Point p, int enemy_ship_part_bg) {
        getByPoint(p).ifPresent(seaBattleCellView ->
                seaBattleCellView.setBg(enemy_ship_part_bg));
    }

    private void showMessage(String message) {
        Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
                .show();
    }

    private Optional<SeaBattleCellView> getByPoint(Point point) {
        return cells.stream()
                .filter(s -> s.getPoint().equals(point))
                .findFirst();
    }

    private void initRows() {
        Coordinates _coordinates = new Coordinates();
        _coordinates.getPoints().forEach(i -> {
            SeaBattleCellView _cellView = new SeaBattleCellView(getContext(), i);
            _cellView.setBg(R.drawable.empty_cell_bg);
            _cellView.setLayoutParams(new ViewGroup.LayoutParams(85, 85));
            initClickListener(_cellView);
            cells.add(_cellView);
        });
    }

    private void initClickListener(@NonNull SeaBattleCellView _cellView) {
        _cellView.setOnClickListener(v -> {
            if (clickable) {
                SeaBattleCellView cell = (SeaBattleCellView) v;
                shot(cell.getPoint());
                if (miss) enemyStepListener.enemyShot();
            }
        });
    }

    private void checkEndGame() {
        for (Ship s: ships){
            if(s.getPoints().size() != 0)
              return;
        }
        endGameListener.end();
    }


    private void initField() {
        LinearLayout _linearLayout = null;
        for (int i = 0; i < cells.size(); i++) {
            if (i % 10 == 0) {
                _linearLayout = new LinearLayout(getContext());
                _linearLayout.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                addView(_linearLayout);
            }
            _linearLayout.addView(cells.get(i));
        }
    }


    public interface EndGameListener{
        void end();
    }
    public void setEndGameListener(EndGameListener endGameListener) {
        this.endGameListener = endGameListener;
    }


    public interface EnemyStepListener{
        void enemyShot();
    }
    public void setEnemyStepListener(EnemyStepListener enemyStepListener) {
        this.enemyStepListener = enemyStepListener;
    }
}
