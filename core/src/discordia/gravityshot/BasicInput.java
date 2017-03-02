package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 1.3.2017.
 */

public class BasicInput implements InputProcessor {
    Projectile shot;
    Rectangle powerBar, sling;
    int charge, maxCharge, initY, initX, angle, maxAngle, width, height;
    ShapeRenderer shaper;
    Vector2 pull;

    public BasicInput(Projectile shot, ShapeRenderer shaper){
        this.shot = shot;
        this.shaper = shaper;
        powerBar = new Rectangle();
        sling = new Rectangle();
        maxCharge = 300;
        maxAngle = 45;
        shot.maxAngle = maxAngle;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        initY = screenY;
        initX = screenX;

        pull = new Vector2(0, 0);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!shot.launched){
            pull.set(screenX-initX, screenY-initY);
            charge = (int) pull.len();
            if(charge > maxCharge) charge = maxCharge; //MAKSIMI-CHARGE

            angle = (int) -pull.angle()+90;
            if(angle < -maxAngle || angle > maxAngle) { //MAX ANGLE
                if(angle < 0) angle = -maxAngle;
                else angle = maxAngle;
            }

            sling.set(initX, height-initY-charge, width/15, charge);

            shot.angle = angle;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!shot.launched){
            shot.shoot(charge);
            charge = 0;
        }
        return false;
    }

    public void draw() {
        //POWERBAR
        shaper.setColor(.5f, 0, 0, 1);
        shaper.rect(-width/2, -height/2, (float)charge/maxCharge*width, height/24);

        //SLINGTRAIL
        shaper.setColor(.2f, .2f, .2f, 1);
        shaper.rect(sling.x-sling.width/2-width/2, sling.y-height/2, sling.width/2, charge, sling.width, charge, 1, 1, angle);
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