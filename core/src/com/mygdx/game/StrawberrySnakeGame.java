package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class StrawberrySnakeGame extends Game {

		SpriteBatch batch;
		BitmapFont bitmapFont;

		@Override
		public void create() {
			bitmapFont = new BitmapFont();
			batch = new SpriteBatch();

			setScreen(new TitleScreen(this));
		}

		@Override
		public void dispose() {
			batch.dispose();
		}
	}