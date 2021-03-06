package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class EndScreen implements Screen {

    StrawberrySnakeGame game;

    public EndScreen(StrawberrySnakeGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.N) {
                    game.setScreen(new TitleScreen(game));
                }
                else if(keyCode == Input.Keys.Y) {
                    System.exit(0);
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.bitmapFont.setColor(Color.PINK);
        game.bitmapFont.getData().setScale(1,1);
        game.bitmapFont.draw(game.batch, "GAME OVER", Gdx.graphics.getWidth() * .45f, Gdx.graphics.getHeight() * .75f);
        game.bitmapFont.draw(game.batch, "Are you sure you want to exit?    Y / N  ", Gdx.graphics.getWidth() * .35f, Gdx.graphics.getHeight() * .65f);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {}
}