package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.StrawberrySnakeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.resizable = false;
		//window size
		config.width = 420;  //660?
		config.height = 225;

		config.title = "Strawberry Snake";

		new LwjglApplication(new StrawberrySnakeGame(), config);
	}
}
