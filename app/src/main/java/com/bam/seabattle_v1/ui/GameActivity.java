package com.bam.seabattle_v1.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bam.seabattle_v1.R;
import com.bam.seabattle_v1.logic.EnemyLogic;
import com.bam.seabattle_v1.logic.Ship;
import com.bam.seabattle_v1.logic.ShipCreator;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    private  SeaBattleFieldView gameField, enemyField;
    private EnemyLogic enemyLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        gameField = findViewById(R.id.field);
        enemyField = findViewById(R.id.enemy_field);

        findViewById(R.id.button).setOnClickListener(v -> {
            startActivity(new Intent(this, HomeActivity.class));
        });


        gameField.setEndGameListener(() -> showDialog("Вы проиграли"));
        enemyField.setEndGameListener(() -> showDialog("Вы победили!"));
        enemyField.setClickable(true);

        initGame();

    }

    private void initGame() {
        enemyLogic = new EnemyLogic();
        ShipCreator userShipCreator = new ShipCreator();
        ShipCreator enemyShipCreator = new ShipCreator();
        List<Ship> userShips = getShips(userShipCreator);
        List<Ship> enemyShips = getShips(enemyShipCreator);
        gameField.setShips(userShips, true);
        enemyField.setShips(enemyShips, false);

        enemyField.setEnemyStepListener(() -> {
            gameField.shot(enemyLogic.getPoint());
        });
    }

    @NonNull
    private List<Ship> getShips(ShipCreator _shipFactory) {
        List<Ship> _ships = new ArrayList<>();
        Ship ships4 = _shipFactory.generate(4,1).get(0);
        _ships.add(ships4);
        List<Ship> ship3 = _shipFactory.generate(3,2);
        _ships.addAll(ship3);
        List<Ship> ship2 = _shipFactory.generate(2, 3);
        _ships.addAll(ship2);
        List<Ship> ship1 = _shipFactory.generate(1,4);
        _ships.addAll(ship1);
        return _ships;
    }


    private void showDialog(String text) {
        new MaterialAlertDialogBuilder(this,
                com.google.android.material.R.style.MaterialAlertDialog_Material3_Body_Text_CenterStacked)
                .setMessage(text).setNegativeButton("Выйти", (dialog, which) -> {
                    startActivity(new Intent(this, HomeActivity.class));
                })
                .setPositiveButton("Новая игра", (dialog, which) -> {
                    initGame();
                })
                .show();
    }
}