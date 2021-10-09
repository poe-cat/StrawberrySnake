package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private boolean canChangeDirection; //can change direction if has changed already

    public Snake(Texture texture) {

        this.texture = texture;

        //default movement direction: up
        direction = MovementDirection.UP;

        snakeParts = new ArrayList<>();

        //snake parts positions
        snakeParts.add(new GridPoint2(90, 30));
        snakeParts.add(new GridPoint2(75, 30));
        snakeParts.add(new GridPoint2(60, 30));
        snakeParts.add(new GridPoint2(45, 30));
        snakeParts.add(new GridPoint2(30, 30));
    }

    //snake'ssss sneaky moves
    public void act(float deltaTime) {

        handleDirectionChange();

        /* When the value of the timeElapsedSinceLastMove
        exceeds the set value 100 milliseconds,
        snake will move and we reset value of timeElapsed...*/

        timeElapsedSinceLastMove += deltaTime;

        if (timeElapsedSinceLastMove >= 0.1) {
            timeElapsedSinceLastMove = 0;
            move();
        }
    }

    //check head's position if it's the same as strawberry's
    public boolean isStrawAboard(GridPoint2 strawPosition) {
        return snakeHead().equals(strawPosition);
    }

    //adding new snake parts
    public void extendSnake() {
        //position of the new one is set to the position of the currently last part
        snakeParts.add(new GridPoint2(snakeParts.get(snakeParts.size() - 1)));
    }

    //collision with himself -> if true, gameOver
    public boolean isHeUroboros() {
        for(int i = 1; i < snakeParts.size(); i++) {
            if(snakeParts.get(i).equals(snakeHead())) {
                return true;
            }
        }
        return false;
    }


    //handling direction change and blocking snake's "eating himself backward"
    //ex: if he's going down, he can't go up
    private void handleDirectionChange() {

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) &&
                direction != MovementDirection.RIGHT) {
            direction = MovementDirection.LEFT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) &&
                direction != MovementDirection.LEFT) {
            direction = MovementDirection.RIGHT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
                direction != MovementDirection.DOWN) {
            direction = MovementDirection.UP;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
                direction != MovementDirection.UP) {
            direction = MovementDirection.DOWN;
        }
    }

    private void move() {

        //move all body parts except the head
        for(int i = snakeParts.size() - 1; i > 0; i--) { //setting snake body part position to next part position
            snakeParts.get(i).set(snakeParts.get(i - 1));
        }

        //moving head
        int partWidth = texture.getWidth();
        int partHeight = texture.getWidth();

        //position X and Y of the last snake part against upper and right window's edge
        int lastWindowPartX = Gdx.graphics.getWidth() - partWidth;
        int lastWindowPartY = Gdx.graphics.getHeight() - partHeight;

        //instead of using "snakeParts.get(0)" as head reference
        GridPoint2 head = snakeHead();

        //Changing direction and handle window edges.
        //ex: if the snake's head is at the left edge of the window,
        //then the x coordinate has the value 0.
        //If so, set this coordinate to the calculated value lastWindowPartX.
        switch(direction) {
            case LEFT:
                head.x = (head.x == 0) ? lastWindowPartX : head.x - partWidth;
                break;
            case UP:
                head.y = (head.y == lastWindowPartY) ? 0 : head.y + partHeight;
                break;
            case RIGHT:
                head.x = (head.x == lastWindowPartX) ? 0 : head.x + partWidth;
                break;
            case DOWN:
                head.y = (head.y == 0) ? lastWindowPartY : head.y - partHeight;
                break;
        }
    }

    //drawing snake body (adding his "parts" to snakeParts ArrayList)
    public void draw(Batch batch) {

        for (GridPoint2 pos : snakeParts) {
            batch.draw(texture, pos.x, pos.y);
        }
    }

    //as separate method for snake's head position
    private GridPoint2 snakeHead() {
        return snakeParts.get(0);
    }
}
