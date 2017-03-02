package discordia.gravityshot;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Main extends ApplicationAdapter {
	ShapeRenderer shaper;
	HomeBase homeBase;
	OrthographicCamera camera;
	Projectile shot;
	BasicInput input;
	
	@Override
	public void create () {
		shaper = new ShapeRenderer();
		homeBase = new HomeBase(shaper);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shot = new Projectile(shaper);
		input = new BasicInput(shot, shaper);

		Gdx.input.setInputProcessor(input);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		shaper.setProjectionMatrix(camera.combined);
		camera.update();

		shaper.begin(ShapeRenderer.ShapeType.Filled);
		shot.drawFlash();
		homeBase.draw();
		shot.draw();
		input.draw();
		shaper.end();
	}
}