package chess;

public class Queen extends Piece {
    public Queen(PlayerColor color, PieceType type) {
        super(color, type);
    }
    @Override
    public boolean canMove() {
        return false;//TODO
    }
};