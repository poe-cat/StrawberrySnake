package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
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

                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new TitleScreen(game));
                }
                else if(keyCode == Input.Keys.F1) {
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
        game.bitmapFont.draw(game.batch, "GAME OVER", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.bitmapFont.draw(game.batch, "Press ENTER to restart", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.bitmapFont.draw(game.batch, "Press F1 to exit", Gdx.graphics.getWidth() * .50f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {

    }
}