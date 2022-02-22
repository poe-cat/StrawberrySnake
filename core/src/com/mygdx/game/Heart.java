package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

public class Heart {

    private final Texture texture;

    public Heart(Texture texture) {
        this.texture = texture;


    }

    public void draw(Batch batch) {
        batch.draw(texture, 800, 410);
    }

    public void extendHearts() {

        //heartParts.add(new GridPoint2(heartParts.get(heartParts.size() - 1)));
    }




}
