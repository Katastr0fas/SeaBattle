package com.bam.seabattle_v1.ui;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;

import com.bam.seabattle_v1.logic.Point;

public class SeaBattleCellView extends FrameLayout {

    private final Point pointId;

    public SeaBattleCellView(@NonNull Context context, Point point) {
        super(context);
        this.pointId = point;
    }

    public void setBg(int drawable){
        setBackground(AppCompatResources.getDrawable(getContext(), drawable));
    }

    public Point getPoint() {
        return pointId;
    }
}
