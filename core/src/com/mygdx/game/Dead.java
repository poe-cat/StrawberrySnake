package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Dead {

    private final Texture texture;

    public Dead(Texture texture) {
        this.texture = texture;
    }

    public void draw(Batch batch) {
        batch.draw(texture, 0, 0);
    }
}
