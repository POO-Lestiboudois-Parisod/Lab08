package chess;

import java.awt.*;
import java.util.ArrayList;

public class King extends SpecialFirstMovePiece implements CastlingPiece {

    public King(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    public boolean canCastle() {
        return true;//TODO
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }

    //TODO
    public void addBeingCheckedBy(Piece piece) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece otherPiece : pieces) {
            if(otherPiece.isAttacking(this.getSquare())){
                pieces.add(this);
            }
        }
    }


};