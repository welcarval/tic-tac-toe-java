package dev.arcturuz.tic_tac_toe;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * ToeField
 */
public class TicTacToe implements ApplicationListener {

    private float VIRTUAL_GAME_HEIGHT = 8;
    private float VIRTUAL_GAME_WIDTH = 8;

    private Vector2 touchPos;

    private SpriteBatch batch;
    private FitViewport boardViewport;
    private ScreenViewport hudViewport;

    private Board board;
    private Texture xPiece;
    private Texture circlePiece;

    Stage stage;

    @Override
    public void create() {
        xPiece = new Texture("x.png");
        xPiece.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        circlePiece = new Texture("circle.png");
        circlePiece.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        batch = new SpriteBatch();
        boardViewport = new FitViewport(VIRTUAL_GAME_WIDTH, VIRTUAL_GAME_HEIGHT);
        board = new Board(batch, boardViewport);
        stage = new Stage(boardViewport);
        Gdx.input.setInputProcessor(stage);

        touchPos = new Vector2();
    }

    @Override
    public void resize(int width, int height) {
        boardViewport.update(width, height, true);
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
            boardViewport.unproject(touchPos);

            if(touchPos.x >= 1 && touchPos.x <= 7 && touchPos.y >= 1 && touchPos.y <= 7) {
                board.addPiece(Math.round(touchPos.x / 2) - 1, Math.round(touchPos.y / 2) - 1);
            }

        }
    }

    private void logic() {

        board.logic();

    }

    private void draw() {
        ScreenUtils.clear(Color.CYAN);
        boardViewport.apply();
        batch.setProjectionMatrix(boardViewport.getCamera().combined);
        batch.begin();

        board.draw();

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
