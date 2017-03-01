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
        width = 9; //Gdx.graphics.getWidth();
        height = 16; //Gdx.graphics.getHeight();
    }

    public void draw() {
        shaper.setColor(0, .5f, .5f, 1);
        //shaper.circle(0, 0, 1);
        shaper.rect(0, 0, 3, 3);
    }
}