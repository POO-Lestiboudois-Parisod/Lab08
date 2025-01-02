package chess;

public class Knight extends Piece {
    public Knight(PlayerColor color, PieceType type, Square square) {
        super(color, type, square);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }
};