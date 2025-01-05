package chess;

abstract class SpecialFirstMovePiece extends Piece {
    protected boolean hasMoved;

    public boolean hasMoved() {
        return true;//TODO
    }

    protected SpecialFirstMovePiece(PlayerColor color, PieceType type) {
        super(color, type);
    }
}
