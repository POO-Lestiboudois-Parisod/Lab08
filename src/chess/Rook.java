package chess;

public class Rook extends SpecialFirstMovePiece implements CastlingPiece {
    public Rook(PlayerColor color) {
        super(color, PieceType.ROOK);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false;
    }

    public boolean canCastle(){
        return true;//TODO
    }

    public boolean canMove(Square square){


        if(square.isOccupied()){
            if(square.getPiece().getColor().equals(this.getColor())){
                return false;
            }
        }

        // Calcul de la distance
        int deltaX = Math.abs(square.getX() - this.square.getX());
        int deltaY = Math.abs(square.getY() - this.square.getY());

        if(deltaX > 0 && deltaY > 0) {
            return false;
        }
        else{
            return true;
        }
    }
};