package chess;

public class Knight extends Piece {
    public Knight(PlayerColor color, PieceType type) {
        super(color, type);
    }

    @Override
    public boolean canMove() {
        return false;//TODO
    }
};