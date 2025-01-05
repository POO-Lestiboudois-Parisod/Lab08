package chess;

import java.awt.*;
import java.util.ArrayList;

public class King extends SpecialFirstMovePiece implements CastlingPiece {

    public King(PlayerColor color) {
        super(color, PieceType.KING);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false;
        //TODO
    }

    @Override
    public boolean canMove(Square square){
        return false;
        //TODO
    }
    public boolean canCastle() {
        return true;//TODO
    }

    //TODO
   /* public void addBeingCheckedBy(Piece piece) {
        ArrayList<Piece> pieces = new ArrayList<>();
        for (Piece otherPiece : pieces) {
            if(otherPiece.isAttacking(this.getSquare())){
                pieces.add(this);
            }
        }
    }*/


};