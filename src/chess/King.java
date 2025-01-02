package chess;

import java.awt.*;

public class King extends Piece {

    public King(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }
};