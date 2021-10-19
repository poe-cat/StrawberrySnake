package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;

public class TitleScreen extends ScreenAdapter {

    StrawberrySnakeGame game;

    TitleScreen(StrawberrySnakeGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.P) {
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
    }
}
