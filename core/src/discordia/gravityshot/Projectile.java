package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Dalud on 1.3.2017.
 */

public class Projectile {
    Rectangle location;
    ShapeRenderer shaper;
    int width, height, angle, maxAngle, countdown;
    float power, maxPower, Angle, color;
    Sound success, failure;
    boolean fail, succeed;

    public Projectile(ShapeRenderer shaper){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        this.shaper = shaper;
        location = new Rectangle(0, -height/2.3f, 7.5f, 42.5f);
        success = Gdx.audio.newSound(Gdx.files.internal("sounds/success.mp3"));
        failure = Gdx.audio.newSound(Gdx.files.internal("sounds/failure.mp3"));
        color = 1;
        countdown = 50;
        maxPower = 15;
    }

    public void draw(){
        location.setPosition(location.x+Angle, location.y+power);

        shaper.setColor(.5f, .5f, .5f, 1);
        shaper.rect(location.x, location.y, 5, 0, location.width, location.height, 1, 1, angle);

        //SUCCESS
        if(location.y > height/2) {
            success.play();
            succeed = true;
            reset();
        }
        //FAILURE
        if(location.x < -width/2 || location.x > width/2){
            failure.play();
            fail = true;
            reset();
        }
    }
    public void drawFlash(){
        //DRAW FLASH
        if(succeed || fail){
            countdown--;
            color = 1f * countdown/100;

            if(succeed) {
                shaper.setColor(0, color, 0, 0);
                shaper.rect(-width/2, -height/2, width, height);
            }
            else if (fail){
                shaper.setColor(color, 0, 0, 0);
                shaper.rect(-width/2, -height/2, width, height);
            }
            if(countdown < 1) {
                countdown = 50;
                color = 1;
                succeed = fail = false;
            }
        }
    }

    private void reset() {
        location.setPosition(0, -height/2.3f);
        power = Angle = angle = 0;
    }

    public void shoot(int charge, int angle) {

        power = charge;
        if(power > maxPower) power = maxPower;
        Angle = -power*angle/maxAngle;
    }
}