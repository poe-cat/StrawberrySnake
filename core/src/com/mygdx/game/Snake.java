package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import java.util.ArrayList;
import java.util.List;

//snake movements directions
enum MovementDirection {
    LEFT, UP, RIGHT, DOWN
}

public class Snake {

    //square size -> size of one snake part
    private static final int PART_WIDTH = 15;
    private static final int PART_HEIGHT = 15;

    //for separate head and tail
    private static final int TEXTURE_HEAD_START_INDEX = 0;
    private static final int TEXTURE_TAIL_START_INDEX = 4;
    private static final int TEXTURE_BODY_INDEX = 8;

    private static final int LAST_POSSIBLE_X_POSITION
            = Gdx.graphics.getWidth() - PART_WIDTH;
    private static final int LAST_POSSIBLE_Y_POSITION
            = Gdx.graphics.getHeight() - PART_HEIGHT;

    private final TextureRegion[] headTexture;
    private final TextureRegion bodyTexture;
    private final TextureRegion[] tailTexture;

    private final List<GridPoint2> snakeParts;

    private MovementDirection direction;
    private MovementDirection tailDirection;

    //moves timing
    private float timeElapsedSinceLastMove;
    //can change direction if has changed already
    private boolean canChangeDirection;


    public Snake(Texture texture) {

        //so now snake contains: head, body & tail

        headTexture = new TextureRegion[] {
                getTexturePart(texture, TEXTURE_HEAD_START_INDEX),
                getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 1),
                getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 2),
                getTexturePart(texture, TEXTURE_HEAD_START_INDEX + 3)
        };

        bodyTexture = getTexturePart(texture, TEXTURE_BODY_INDEX);

        tailTexture = new TextureRegion[] {
                getTexturePart(texture, TEXTURE_TAIL_START_INDEX),
                getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 1),
                getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 2),
                getTexturePart(texture, TEXTURE_TAIL_START_INDEX + 3)
        };

        snakeParts = new ArrayList<>();
    }


    public void initialize() {

        //to restart after game over

        timeElapsedSinceLastMove = 0;

        //default direction after restart
        direction = MovementDirection.RIGHT;
        tailDirection = MovementDirection.RIGHT;

        //clear ArrayList<> -> remove old snake parts
        snakeParts.clear();

        //new snake
        snakeParts.add(new GridPoint2(90, 30));
        snakeParts.add(new GridPoint2(75, 30));
        snakeParts.add(new GridPoint2(60, 30));
        snakeParts.add(new GridPoint2(45, 30));
        snakeParts.add(new GridPoint2(30, 30));
    }

    public void act(float deltaTime) {

        if(canChangeDirection) {
            handleDirectionChange();
        } //if not, he won't move

        /* When the value of the timeElapsedSinceLastMove
        exceeds the set value 100 milliseconds,
        snake will move and we reset value of timeElapsed...*/
        timeElapsedSinceLastMove += deltaTime;

        if (timeElapsedSinceLastMove >= 0.1) {
            timeElapsedSinceLastMove = 0;
            canChangeDirection = true; //true if snake's already moved
            move();
        }

        determineTailDirection();
    }


    public boolean isStrawAboard(GridPoint2 strawPosition) {

        //check head's position if it's the same as strawberry's

        return snakeHead().equals(strawPosition);
    }


    public void extendSnake() {

        //adding new snake parts
        //position of the new one is set
        // to the position of the currently last part

        snakeParts.add(new GridPoint2(snakeParts.get(snakeParts.size() - 1)));
    }


    public boolean isHeUroboros() {

        //collision with himself -> if true, gameOver

        for(int i = 1; i < snakeParts.size(); i++) {
            if(snakeParts.get(i).equals(snakeHead())) {
                return true;
            }
        }
        return false;
    }

    public void draw(Batch batch) {

        //draw snake's body (without head and tail)
        for (int i = 1; i < snakeParts.size() - 1; i++) {
            GridPoint2 body = snakeParts.get(i);
            batch.draw(bodyTexture, body.x, body.y);
        }

        //draw snake's tail
        GridPoint2 tail = snakeParts.get(tailIndex());
        batch.draw(
                tailTexture[tailDirection.ordinal()],
                tail.x,
                tail.y
        );

        //draw snake's head
        batch.draw(
                headTexture[direction.ordinal()],
                snakeHead().x,
                snakeHead().y
        );
    }

    private TextureRegion getTexturePart(Texture texture, int index) {

        //image contains textures of the same size, in one row:
        //the index argument sets which texture we want to download next

        return new TextureRegion(
                texture,
                index * PART_WIDTH,
                0,
                PART_WIDTH,
                PART_HEIGHT
        );
    }


    private void handleDirectionChange() {
        //enum newDirection
        MovementDirection newDirection = direction;

        //handling direction change and blocking
        // snake's "eating himself backward"
        //ex: if he's going down, he can't go up

        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) &&
                direction != MovementDirection.RIGHT) {
            newDirection = MovementDirection.LEFT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) &&
                direction != MovementDirection.LEFT) {
            newDirection = MovementDirection.RIGHT;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) &&
                direction != MovementDirection.DOWN) {
            newDirection = MovementDirection.UP;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) &&
                direction != MovementDirection.UP) {
            newDirection = MovementDirection.DOWN;
        }

        if (direction != newDirection) {
            direction = newDirection;
            canChangeDirection = false;
        }
    }

    private void move() {

        //move all body parts except the head
        //setting snake body part position to next part position
        for(int i = snakeParts.size() - 1; i > 0; i--) {
            snakeParts.get(i).set(snakeParts.get(i - 1));
        }

        GridPoint2 head = snakeHead();

        //Changing direction and handle window edges.
        //ex: if the snake's head is at the left edge of the window,
        //then the x coordinate has the value 0.
        //If so, set this coordinate to the calculated value
        switch(direction) {
            case LEFT:
                head.x = (head.x == 0) ?
                        LAST_POSSIBLE_X_POSITION : head.x - PART_WIDTH;
                break;
            case UP:
                head.y = (head.y == LAST_POSSIBLE_Y_POSITION)
                        ? 0 : head.y + PART_HEIGHT;
                break;
            case RIGHT:
                head.x = (head.x == LAST_POSSIBLE_X_POSITION) ?
                        0 : head.x + PART_WIDTH;
                break;
            case DOWN:
                head.y = (head.y == 0) ?
                        LAST_POSSIBLE_Y_POSITION : head.y - PART_HEIGHT;
                break;
        }
    }

    private void determineTailDirection() {
        GridPoint2 partBeforeTail = snakeParts.get(tailIndex() - 1);
        GridPoint2 tail = snakeParts.get(tailIndex());

        // 4 cases -> four window edges
        if(tail.x == 0 &&
                partBeforeTail.x == LAST_POSSIBLE_X_POSITION) {

            tailDirection = MovementDirection.LEFT;

        }else if (tail.x == LAST_POSSIBLE_X_POSITION &&
                partBeforeTail.x == 0) {

            tailDirection = MovementDirection.RIGHT;

        }else if (tail.y == 0 &&
                partBeforeTail.y == LAST_POSSIBLE_Y_POSITION) {

            tailDirection = MovementDirection.DOWN;

        }else if (tail.y == LAST_POSSIBLE_Y_POSITION &&
                partBeforeTail.y == 0) {

            tailDirection = MovementDirection.UP;

        }
        //checking which side of the tail (last snake part),
        //is the one before last part
        else if(partBeforeTail.x > tail.x) {
            tailDirection = MovementDirection.RIGHT;
        }else if(partBeforeTail.x < tail.x) {
            tailDirection = MovementDirection.LEFT;
        }else if(partBeforeTail.y > tail.y) {
            tailDirection = MovementDirection.UP;
        }else if(partBeforeTail.y < tail.y) {
            tailDirection = MovementDirection.DOWN;
        }
    }


    private GridPoint2 snakeHead() {
        return snakeParts.get(0);
    }

    private int tailIndex() {
        return snakeParts.size() - 1;
    }
}

