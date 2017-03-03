package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 2.3.2017.
 */

public class Planet {
    int width, height;
    float radius, r, g, b, gravityF;
    Circle location;
    Vector2 gravityD;


    public Planet(){
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        reset();
    }

    public void draw(ShapeRenderer shaper){
        shaper.setColor(r, g, b, 1);
        shaper.circle(location.x, location.y, radius);
    }

    public void reset(){    //JOSTAIN ***** SYYSTÄ KONSTRUKTORIN UUDELLEEN KUTSUMINEN EI ASETA LOCATION CIRCLEÄ, SIKSI TÄMÄ ABOMINAATIO
        float Radius = (float) Math.random();
        radius = Radius*width/3;
        r = (float)Math.random();
        g = (float)Math.random();
        b = (float)Math.random();
        location = new Circle( (float)(width*Math.random()-width/3), (float) (height/3*Math.random()), radius );
        gravityD = new Vector2(location.x, location.y);
        gravityF = Radius/1.5f;
        gravityD.setLength(gravityF);
    }
}