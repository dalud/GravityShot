package discordia.gravityshot;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Dalud on 1.3.2017.
 */

public class HomeBase {
    ShapeRenderer shaper;
    int width, height;

    public HomeBase(ShapeRenderer shaper){
        this.shaper = shaper;
        width = 900; //Gdx.graphics.getWidth();
        height = 1600; //Gdx.graphics.getHeight();
    }

    public void draw() {
        shaper.setColor(0, .3f, .3f, 1);
        shaper.circle(0, -height/1.3f, width/1.5f);
    }
}