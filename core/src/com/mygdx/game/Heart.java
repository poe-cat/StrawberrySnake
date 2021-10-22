package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Heart {

    private final GridPoint2 position;
    private final Texture texture;

    public Heart(GridPoint2 position, Texture texture) {
        this.position = position;
        this.texture = texture;
    }

    public void draw(Batch batch) {
        batch.draw(texture, 400, 400);
    }



}
