package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


/**
 * Created by Dalud on 1.3.2017.
 */

public class BasicInput implements InputProcessor {
    Projectile shot;
    Rectangle powerBar;
    int charge, initY, initX, angle;
    ShapeRenderer shaper;


    public BasicInput(Projectile shot, ShapeRenderer shaper){
        this.shot = shot;
        this.shaper = shaper;
        powerBar = new Rectangle();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        initY = screenY;
        initX = screenX;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        charge = screenY - initY;
        if(charge < 0) charge = 0;
        angle = (screenX - initX) * 45 / Gdx.graphics.getWidth();
        if(angle < -45 || angle > 45) {
            if(angle < 0) angle = -45;
            else angle = 45;
        }

        powerBar.set(shot.location.x, shot.location.y-charge, 10, charge);
        shot.angle = angle;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        shot.shoot(charge, angle);
        charge = 0;

        return false;
    }

    public void draw() {
        shaper.setColor(1, 0, 0, 1);
        shaper.rect(shot.location.x, shot.location.y-charge, 5, charge, 10, charge, 1, 1, angle);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}