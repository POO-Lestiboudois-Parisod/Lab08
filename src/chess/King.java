package chess;

import java.awt.*;

public class King extends Piece {

    public King(PlayerColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }
};