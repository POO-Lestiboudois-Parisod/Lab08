package chess;

public class Rook extends Piece {
    public Rook(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }
    @Override
    public boolean canMove() {
        return false;//TODO
    }
};