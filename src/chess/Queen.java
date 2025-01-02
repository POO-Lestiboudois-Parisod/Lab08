package chess;

public class Queen extends Piece {
    public Queen(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }
    @Override
    public boolean canMove() {
        return false;//TODO
    }
};