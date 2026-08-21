package dev.arcturuz.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Board {

    private Texture toeField;
    private SpriteBatch batch;
    private Piece[][] pieces;
    private PieceType turn;
    private EndGameLine endGameLine;
    private Viewport viewport;

    public Board(SpriteBatch batch, Viewport viewport) {
        this.batch = batch;
        this.viewport = viewport;
        this.turn = PieceType.X;
        this.toeField = new Texture("field.png");
        this.toeField.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.endGameLine = null;

        pieces = new Piece[3][3];
    }

    public void addPiece(int x, int y) {
        if (this.pieces[x][y] == null && this.endGameLine == null) {
            this.pieces[x][y] = new Piece(batch, this.turn, x, y);
            this.turn = this.turn == PieceType.X ? PieceType.CIRCLE : PieceType.X;
        }
    }

    public void logic() {
        for (int i = 0; i < 3; i++) {
            if (pieces[i][0] != null && pieces[i][0].equals(pieces[i][1]) && pieces[i][0].equals(pieces[i][2])) {
                this.endGameLine = new EndGameLine(i * 2 + 1, 0, i * 2 + 1, 6);
                return;

            }

            if (pieces[0][i] != null && pieces[0][i].equals(pieces[1][i]) && pieces[0][i].equals(pieces[2][i])) {
                this.endGameLine = new EndGameLine(0, i * 2 + 1, 6, i * 2 + 1);
                return;
            }
        }

        if (pieces[0][0] != null && pieces[0][0].equals(pieces[1][1]) && pieces[1][1].equals(pieces[2][2])) {
            this.endGameLine = new EndGameLine(0, 0, 6, 6);
        }

        if (pieces[2][0] != null && pieces[2][0].equals(pieces[1][1]) && pieces[1][1].equals(pieces[0][2])) {
            this.endGameLine = new EndGameLine(6, 0, 0, 6);
        }
    }

    public void draw() {
        batch.draw(toeField, 1, 1, 6, 6);

        for (int i = 0; i < pieces.length; i++) {
            for (int j = 0; j < pieces.length; j++) {
                if (pieces[i][j] != null) {
                    pieces[i][j].draw();
                }
            }
        }

        if (this.endGameLine != null){
            this.batch.end();
            this.endGameLine.draw(viewport);
            this.batch.begin();
        }
    }

}
