package chess;

public class Pawn extends Piece implements PromotablePiece{
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canPromote(){
        return true;//TODO
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false; // TODO
    }

    @Override
    public boolean canMove(Square square){
        return false;
        //TODO
    }
};