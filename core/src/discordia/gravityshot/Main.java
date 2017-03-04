package discordia.gravityshot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

public class Main extends ApplicationAdapter {
	ShapeRenderer shaper;
	HomeBase homeBase;
	OrthographicCamera camera;
	Projectile shot;
	BasicInput input;
	Planet test, test2;
	Planets planets;

	@Override
	public void create () {
		shaper = new ShapeRenderer();
		homeBase = new HomeBase();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		test = new Planet();
		test2 = new Planet();
		planets = new Planets();
		shot = new Projectile(planets);
		input = new BasicInput(shot);

		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shaper.setProjectionMatrix(camera.combined);
		camera.update();

		shaper.begin(ShapeRenderer.ShapeType.Filled);
		shot.drawFlash(shaper);
		homeBase.draw(shaper);
		shot.draw(shaper);
		planets.draw(shaper);
		input.draw(shaper);
		shaper.end();
	}
}