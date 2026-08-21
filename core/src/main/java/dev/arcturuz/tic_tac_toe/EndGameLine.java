package dev.arcturuz.tic_tac_toe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class EndGameLine {

    private ShapeRenderer renderer;
    private int xi;
    private int yi;
    private int xf;
    private int yf;

    public EndGameLine(int xi, int yi, int xf, int yf) {
        this.renderer = new ShapeRenderer();
        this.xi = xi;
        this.yi = yi;
        this.xf = xf;
        this.yf = yf;
    }

    public void draw(Viewport viewport) {
        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.rectLine(1 + xi, 1 + yi, 1 + xf, 1 + yf, .2f);
        renderer.end();
    }
}
