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

        super.canMove(square);


        // Calcul de la distance
        double deltaX = distanceX(square);
        double deltaY = distanceY(square);

        // Le roi peut se déplacer d'une case dans n'importe quelle direction
        return (deltaX <= Math.sqrt(2) && deltaY <= Math.sqrt(2));

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