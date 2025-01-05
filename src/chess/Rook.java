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
};