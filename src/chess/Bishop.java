package chess;

import java.awt.*;

public class Bishop extends Piece {

    public Bishop(PlayerColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove(){
        return false; //TODO
    }
}