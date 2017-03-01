package discordia.gravityshot.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import discordia.gravityshot.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		System.setProperty("user.name", "Mitja");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 9*50;
		config.height = 16*50;
		new LwjglApplication(new Main(), config);
	}
}
