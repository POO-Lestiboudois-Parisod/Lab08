package chess;

public class Rook extends SpecialFirstMovePiece implements CastlingPiece {
    public Rook(PlayerColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false;
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }

    public boolean canCastle(){
        return true;//TODO
    }
};