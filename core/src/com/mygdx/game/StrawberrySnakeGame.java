package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StrawberrySnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture snakeImg;
	private Snake snake;

	@Override
	public void create() {
		batch = new SpriteBatch();
		snakeImg = new Texture("snake.png");
		snake = new Snake(snakeImg);
	}

	@Override
	public void render() {
		snake.act(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		//drawing snake
		snake.draw(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		batch.dispose();
		//release texture (snake is moving on)
		snakeImg.dispose();
	}
}