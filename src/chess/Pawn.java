package chess;

public class Pawn extends Piece {
    public Pawn(PlayerColor color, PieceType type) {
        super(color, type);
    }
    @Override
    public boolean canMove() {
        return false;//TODO
    }
};