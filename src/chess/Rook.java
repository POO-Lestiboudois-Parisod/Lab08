package chess;

public class Rook extends SpecialFirstMovePiece implements CastlingPiece {
    public Rook(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }

    public boolean canCastle(){
        return true;//TODO
    }
};