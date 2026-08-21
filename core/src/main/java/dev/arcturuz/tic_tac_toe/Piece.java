package dev.arcturuz.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Piece {

    private SpriteBatch batch;
    private Texture texture;
    private PieceType type;
    private int x;
    private int y;

    public Piece(SpriteBatch batch, PieceType type, int x, int y) {
        this.batch = batch;
        this.type = type;
        this.x = x;
        this.y = y;

        texture = new Texture(String.format("%s", type == PieceType.X ? "x.png" : "circle.png"));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public void draw() {
        batch.draw(texture, 1.25f + x * 2, 1.25f + y * 2, 1.5f, 1.5f);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Piece) {
            Piece p = (Piece) obj;
            return this.type == p.type;
        }
        return false;
    }
}
