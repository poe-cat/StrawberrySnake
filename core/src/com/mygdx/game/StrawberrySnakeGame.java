package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StrawberrySnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture strawImg;

	private Snake snake;
	private Strawberry strawberry;

	private boolean gameOver;


	@Override
	public void create() {
		batch = new SpriteBatch();
		snakeImg = new Texture("snake.png");
		strawImg = new Texture("straw2.png");

		snake = new Snake(snakeImg);
		strawberry = new Strawberry(strawImg);
	}

	@Override
	public void render() {
		snake.act(Gdx.graphics.getDeltaTime());

		//if head has the same pos as strawberry, snake extends and new food randomly appears
		if(snake.isStrawAboard(strawberry.getPosition())) {
			snake.extendSnake();
			strawberry.randomizeFoodPos();
		}

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		//drawing snake and strawberry
		snake.draw(batch);
		strawberry.draw(batch);

		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();

		//release texture (snake is moving on, strawberry is eaten)
		snakeImg.dispose();
		strawImg.dispose();
	}
}