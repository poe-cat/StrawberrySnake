package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GameOver {

    private final Texture texture;

    public GameOver(Texture texture) {
        this.texture = texture;
    }

    public void draw(Batch batch) {
        batch.draw(texture, 180, 50);
    }
}


