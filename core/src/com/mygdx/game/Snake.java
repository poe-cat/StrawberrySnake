package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;
import java.util.List;

//snake movements directions
enum MovementDirection {
    LEFT, UP, RIGHT, DOWN
}

public class Snake {

    private final Texture texture;
    private final List<GridPoint2> snakeParts;
    private MovementDirection direction;
    private float timeElapsedSinceLastMove; //moves timing

    public Snake(Texture texture) {

        this.texture = texture;

        //default movement direction: up
        direction = MovementDirection.UP;

        snakeParts = new ArrayList<>();

        snakeParts.add(new GridPoint2(90, 30));
        snakeParts.add(new GridPoint2(75, 30));
        snakeParts.add(new GridPoint2(60, 30));
        snakeParts.add(new GridPoint2(45, 30));
        snakeParts.add(new GridPoint2(30, 30));
    }

    //snake'ssss sneaky moves
    public void act(float deltaTime) {

        /* When the value of the timeElapsedSinceLastMove
        exceeds the set value 100 milliseconds,
        snake will move and we reset value of timeElapsed...*/

        timeElapsedSinceLastMove += deltaTime;

        if (timeElapsedSinceLastMove >= 0.1) {
            timeElapsedSinceLastMove = 0;
            move();
        }
    }



    private void move() {

        for(int i = snakeParts.size() - 1; i > 0; i--) { //setting snake body part position to next part position
            snakeParts.get(i).set(snakeParts.get(i - 1)); //each snake part is moving on (except the first one)
        }

        //moving the head (first body part)
        GridPoint2 head = snakeParts.get(0);

            //changing direction
            switch (direction) {
                case LEFT:
                    head.x -= texture.getWidth();
                    break;
                case UP:
                    head.y += texture.getHeight();
                    break;
                case RIGHT:
                    head.x += texture.getWidth();
                    break;
                case DOWN:
                    head.y -= texture.getHeight();
                    break;
            }
        }




    //drawing snake body (adding his "parts" to snakeParts ArrayList)
    public void draw(Batch batch) {

        for (GridPoint2 pos : snakeParts) {
            batch.draw(texture, pos.x, pos.y);
        }
    }
}
