package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StrawberrySnakeGame extends ApplicationAdapter {

	private SpriteBatch batch;
	private Texture snakeImg;
	private Texture strawImg;

	private Snake snake;
	private Strawberry strawberry;
	private boolean gameOver;

	private int score;
	private String yourScore;
	BitmapFont bitmapFont;

	Sound soundNom;
	Sound soundCrash;
	Music music;

	private String gameOverStr;
	BitmapFont gameOverFont;


	@Override
	public void create() {

		score = 0;
		yourScore = "score: 0";
		bitmapFont = new BitmapFont();

		music = Gdx.audio.newMusic(Gdx.files.internal("core/assets/8bit_bg.mp3"));
		soundNom = Gdx.audio.newSound(Gdx.files.internal("core/assets/eat_food.mp3"));
		soundCrash = Gdx.audio.newSound(Gdx.files.internal("core/assets/crash.ogg"));

		batch = new SpriteBatch();
		snakeImg = new Texture("pinkSnake.png");
		strawImg = new Texture("straw2.png");

		snake = new Snake(snakeImg);
		strawberry = new Strawberry(strawImg);

		initNewGame();
	}

	//start new game after game over
	private void initNewGame() {
		snake.initialize();
		score = 0;
		yourScore = "score: 0";
		strawberry.randomizeFoodPos();
		gameOver = false;
	}

	@Override
	public void render() {

		runningGame();

		music.play();

		Gdx.gl.glClearColor(.1f, 0.4f, 0.6f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		//drawing snake and strawberry
		snake.draw(batch);
		strawberry.draw(batch);

		//display score
		bitmapFont.setColor(118, 140, 0, 1);
		bitmapFont.draw(batch, yourScore, 400, 440);

		batch.end();
	}

	//game logic
	private void runningGame() {

		if (!gameOver) {
			snake.act(Gdx.graphics.getDeltaTime());

			//if head has the same pos as strawberry,
			// snake extends and new food randomly appears
			if (snake.isStrawAboard(strawberry.getPosition())) {
				soundNom.play();
				snake.extendSnake();
				score++;
				yourScore = "score: " + score;
				strawberry.randomizeFoodPos();
			}

			if (snake.isHeUroboros()) {
				soundCrash.play();
				gameOver = true;
				System.out.println("GAME OVER\nPRESS ENTER TO PLAY AGAIN");
			}
		} else {
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
				initNewGame();
			}
		}
	}

	@Override
	public void dispose() {
		batch.dispose();

		//release texture (snake is moving on, strawberry is eaten)
		snakeImg.dispose();
		strawImg.dispose();
		soundNom.dispose();
		soundCrash.dispose();
		music.dispose();
	}
}