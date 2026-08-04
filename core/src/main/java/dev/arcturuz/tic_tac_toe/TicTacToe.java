package dev.arcturuz.tic_tac_toe;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * ToeField
 */
public class TicTacToe implements ApplicationListener {

    private SpriteBatch batch;
    private FitViewport viewport;

    private Texture toeField;
    private Texture x;
    private Texture circle;

    @Override
    public void create() {
        toeField = new Texture("field.png");
        x = new Texture("x.png");
        circle = new Texture("circle.png");

        batch = new SpriteBatch();
        viewport = new FitViewport(5, 5);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        input();
        logic();
        draw();
    }

    private void input() {

    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.CYAN);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(toeField, 0, 0, 5, 5);

        batch.end();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
