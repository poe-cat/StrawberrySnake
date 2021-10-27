package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

public class TitleScreen extends ScreenAdapter {

    StrawberrySnakeGame game;

    public TitleScreen(StrawberrySnakeGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new GameScreen(game));
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
        game.bitmapFont.draw(game.batch, "Strawberry Snake", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.bitmapFont.draw(game.batch, "use arrows to chase the strawberry", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .5f);
        game.bitmapFont.draw(game.batch, "press ENTER to play", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.end();
    }


    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
