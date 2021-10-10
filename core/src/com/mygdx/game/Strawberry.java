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

    //draw strawberry
    public void draw(Batch batch) {
        batch.draw(texture, position.x, position.y);
    }

    //randomize the food position
    public void randomizeFoodPos() {

        //number of possible x&y positions in the game window
        int numOfXPositions =
                Gdx.graphics.getWidth() / texture.getWidth();
        int numOfYPositions =
                Gdx.graphics.getHeight() / texture.getHeight();

        //multiply random values by the width/height of the food texture
        //to match food to the "position grid"
        this.position.set(
                (int)(Math.random() * numOfXPositions) * texture.getWidth(),
                (int)(Math.random() * numOfYPositions) * texture.getHeight()
        );
    }

    public GridPoint2 getPosition() {
        return this.position;
    }
}
