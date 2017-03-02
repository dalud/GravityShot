package discordia.gravityshot;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Dalud on 1.3.2017.
 */

public class HomeBase {
    ShapeRenderer shaper;
    int width, height;

    public HomeBase(ShapeRenderer shaper){
        this.shaper = shaper;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }

    public void draw() {
        shaper.setColor(0, .3f, .3f, 1);
        shaper.circle(0, -height*.95f, width);
    }
}