package chess;

public class Rook extends Piece {
    public Rook(PlayerColor color, PieceType type) {
        super(color, type);
    }
    @Override
    public boolean canMove() {
        return false;//TODO
    }
};