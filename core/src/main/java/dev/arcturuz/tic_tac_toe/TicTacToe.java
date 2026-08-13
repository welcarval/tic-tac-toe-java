package dev.arcturuz.tic_tac_toe;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * ToeField
 */
public class TicTacToe implements ApplicationListener {

    private Vector2 touchPos;

    private SpriteBatch batch;
    private FitViewport viewport;

    private Texture toeField;
    private Texture xPiece;
    private Texture circlePiece;

    private Boolean XTime;

    private Map<Integer,String> pieces;

    @Override
    public void create() {
        toeField = new Texture("field.png");
        xPiece = new Texture("x.png");
        circlePiece = new Texture("circle.png");
        XTime = true;

        batch = new SpriteBatch();
        viewport = new FitViewport(8, 8);
        touchPos = new Vector2();
        pieces = new HashMap<>();
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
        if (Gdx.input.isTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);
            System.out.println("x: " + touchPos.x + ", y: " + touchPos.y);

            Integer newPieceX = 0;
            Integer newPieceY = 0;

            if (touchPos.x >= 1 && touchPos.x < 3) {
                newPieceX = 0;
            } else if (touchPos.x >= 3 && touchPos.x < 5) {
                newPieceX = 1;
            } else if (touchPos.x >= 5 && touchPos.x < 7) {
                newPieceX = 2;
            }

            if (touchPos.y >= 1 && touchPos.y < 3) {
                newPieceY = 0;
            } else if (touchPos.y >= 3 && touchPos.y < 5) {
                newPieceY = 1;
            } else if (touchPos.y >= 5 && touchPos.y < 7) {
                newPieceY = 2;
            }

            if (touchPos.x >= 1 && touchPos.x < 7 && touchPos.y >= 1 && touchPos.y < 7 && pieces.get(newPieceX * 3 + newPieceY) == null) {
                if (XTime) {
                    pieces.put(newPieceX * 3 + newPieceY, "x");
                    XTime = false;
                } else {
                    pieces.put(newPieceX * 3 + newPieceY, "circle");
                    XTime = true;
                }
            }
        }
    }

    private void logic() {

    }

    private void draw() {
        ScreenUtils.clear(Color.CYAN);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        batch.draw(toeField, 1, 1, 6, 6);

        for (Integer pos : pieces.keySet()) {
            if (pieces.get(pos) == "x") {
                batch.draw(xPiece, 1.25f + (pos / 3) * 2, 1.25f + (pos % 3) * 2, 1.5f, 1.5f);
            } else {
                batch.draw(circlePiece, 1.25f + (pos / 3) * 2, 1.25f + (pos % 3) * 2, 1.5f, 1.5f);
            }
        }

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
