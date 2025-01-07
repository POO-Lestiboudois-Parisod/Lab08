package chess;

public class Pawn extends Piece implements PromotablePiece {
    public Pawn(PlayerColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public boolean canPromote() {
        return true;//TODO
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        return false; // TODO
    }

    @Override
    public boolean canMove(Square square) {
        //TODO prise en passant

        boolean canMove = (distanceX(square) == 1 && distanceY(square) == 1);

        if (getColor().equals(PlayerColor.WHITE)) {
            canMove = canMove && (this.square.getY() < square.getX());
        } else {
            canMove = canMove && (this.square.getY() > square.getX());

        }
            return canMove;
    }
}