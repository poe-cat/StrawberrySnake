package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;
import java.util.List;


public class Snake {

    private final Texture texture;
    private final List<GridPoint2> snakeParts;

    public Snake(Texture texture) {

        this.texture = texture;

        snakeParts = new ArrayList<>();

        snakeParts.add(new GridPoint2(90, 30));
        snakeParts.add(new GridPoint2(75, 30));
        snakeParts.add(new GridPoint2(60, 30));
        snakeParts.add(new GridPoint2(45, 30));
        snakeParts.add(new GridPoint2(30, 30));
    }

    //drawing snake body (adding his "parts" to snakeParts ArrayList)
    public void draw(Batch batch) {

        for (GridPoint2 pos : snakeParts) {
            batch.draw(texture, pos.x, pos.y);
        }
    }
}
