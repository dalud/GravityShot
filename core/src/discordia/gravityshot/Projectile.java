package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 1.3.2017.
 */

public class Projectile {
    Rectangle location;
    int width, height, countdown;
    float angle, color;
    Sound success, failure;
    boolean fail, succeed, launched;
    Planets planets;
    Vector2 velocity, position;

    public Projectile(Planets planets){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        location = new Rectangle(-width/40/2, -height/2.35f, width/40, height/20);
        success = Gdx.audio.newSound(Gdx.files.internal("sounds/success.mp3"));
        failure = Gdx.audio.newSound(Gdx.files.internal("sounds/failure.mp3"));
        color = 1;
        countdown = 50;
        this.planets = planets;
        velocity = new Vector2();
        position = location.getPosition(new Vector2());
    }

    public void draw(ShapeRenderer shaper){
        calculateDeviation();

        location.setPosition(location.getPosition(position).add(velocity));
        if(launched)angle = velocity.angle()+90;

        shaper.setColor(.5f, .5f, .5f, 1);
        shaper.rect(location.x, location.y, location.width/2, location.height/2, location.width, location.height, 1, 1, angle);

        //SUCCESS
        if(location.y > height/2-location.height) {
            success.play();
            succeed = true;
            reset();
        }
        //FAILURE
        if((location.x < -width/2) || (location.x > width/2) || checkCollision(planets)){
            failure.play();
            fail = true;
            reset();
        }
    }

    private void calculateDeviation() {
        if(launched) {
            for(Planet check : planets.planets){
                check.gravityD.set(check.location.x-location.x, check.location.y-location.y);
                check.gravityD.setLength(check.gravityF);
                velocity.add(check.gravityD);
            }
        }
    }

    public void drawFlash(ShapeRenderer shaper){
        //DRAW FLASH
        if(succeed || fail){
            countdown--;
            color = 1f * countdown/100;

            if(succeed) {
                shaper.setColor(0, color, 0, 0);
                shaper.rect(-width/2, -height/2, width, height);
                planets.reset();
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
        launched = false;
        location.setPosition(0-location.width/2, -height/2.35f);
        velocity = new Vector2();
        angle = 0;
    }

    public void shoot(Vector2 pull) {
        pull.setLength(pull.len()/width*10); //SKAALATAAN POWERBARIN MUKAISEKSI
        if(pull.len() < 3) pull.setLength(3);
        if(!launched){
            velocity = pull;
            launched = true;
        }
    }

    public boolean checkCollision(Planets planets){ //PARAMETRI, KOSKA TULEE LISÄÄ PLANEETTOJA JOSKUS EHKÄ
        for(Planet check : planets.planets){
            if((int)(location.getPosition(position).sub(new Vector2(check.location.x, check.location.y)).len()-check.radius) < 0) return true;
        }
        return false;
    }
}