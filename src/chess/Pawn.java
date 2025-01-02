package chess;

public class Pawn extends Piece implements PromotablePiece{
    public Pawn(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }

    @Override
    public boolean canPromote(){
        return true;//TODO
    }
};