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
    Vector2 pull;

    public BasicInput(Projectile shot){
        this.shot = shot;
        powerBar = new Rectangle();
        sling = new Rectangle();
        maxAngle = 60;
        width = Gdx.graphics.getWidth();
        maxCharge = width;
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
            if(screenY < initY) {
                screenY = initY; //EI ANNETA AMPUA TAAKSEPÄIN
                pull.setLength(0);
            }
            else pull.set(screenX-initX, screenY-initY);

            charge = (int) pull.len()*2; //SKAALATAAN JÄRKEVÄMMÄKSI, ETTEI TARVI RAAHATA KOKO NÄYTÖN PITUUTTA

            if(charge > maxCharge) charge = maxCharge; //MAKSIMI-CHARGE

            angle = (int) -pull.angle()+90;
            if(angle < -maxAngle || angle > maxAngle) { //MAX ANGLE
                if(angle < 0) angle = -maxAngle;
                else angle = maxAngle;
            }

            sling.set(initX, height/2-initY-(screenY-initY), width/15, screenY-initY);

            shot.angle = angle;
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(!shot.launched){
            pull.setLength(charge);
            if(charge > 0) shot.shoot(new Vector2(-pull.x, pull.y));
            else shot.angle = 0;
            charge = 0;
        }
        sling.height = 0;
        return false;
    }

    public void draw(ShapeRenderer shaper) {
        //POWERBAR
        shaper.setColor(.5f, 0, 0, 1);
        shaper.rect(-width/2, -height/2, charge, height/24);

        //SLINGTRAIL
        shaper.setColor(.2f, .2f, .2f, 1);
        shaper.rect(sling.x-width/2-shot.location.width/2, sling.y, sling.width/2, sling.height, sling.width, sling.height, 1, 1, angle);
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