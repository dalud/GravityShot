package discordia.gravityshot;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dalud on 4.3.2017.
 */

//SÄILIÖ VAIHTUVAN MÄÄRÄN PLANEETTOJA SYNNYTTÄMISEEN

public class Planets {
    Planet[] planets;

    public Planets(){
        reset();
    }

    public void draw(ShapeRenderer shaper) {
        for(Planet p : planets) p.draw(shaper);
    }

    public void reset() { //SAMASTA SYYSTÄ KUN PLANET-RESET (mistähän ******* johtuu, ettei se resettaa noita renderöintejä...)
        int amount = (int) (Math.random()*3+1);
        planets = new Planet[amount];
        for(int i=0; i< amount; i++){
            planets[i] = new Planet();
        }
        for (Planet p : planets) p.reset();
        organize();
    }

    public void organize() {
        Planet a = planets[0];

        for (Planet p : planets){
            Vector2 check = new Vector2(p.location.x, p.location.y);
            if(new Vector2(a.location.x, a.location.y).sub(check).len()+a.radius < p.radius) p.reset(); //ANNETAAN YKS MAHOLLISUUS SPAWNAA PAREMPAAN PAIKKAAN :D
            a = p;
        }
    }
}