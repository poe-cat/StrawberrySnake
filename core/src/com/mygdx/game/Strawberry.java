package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;

public class Strawberry {

    private final GridPoint2 position;
    private final Texture texture;

    public Strawberry(Texture texture) {
        this.texture = texture;
        this.position = new GridPoint2();
    }


}
