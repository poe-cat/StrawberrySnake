package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;

public class Strawberry {
    private final GridPoint2 position;
    private final Texture texture;

    public Strawberry(Texture texture) {
        this.texture = texture;
        this.position = new GridPoint2();
    }

    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    public void randomizeFoodPos() {
        int numOfXPositions =
                Gdx.graphics.getWidth() / texture.getWidth();
        int numOfYPositions =
                Gdx.graphics.getHeight() / texture.getHeight();

        this.position.set(
                (int)(Math.random() * numOfXPositions) * texture.getWidth(),
                (int)(Math.random() * numOfYPositions) * texture.getHeight()
        );
    }

    public GridPoint2 getPosition() {
        return this.position;
    }
}
