package chess;

public class Knight extends Piece {

    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier que le mouvement est en forme de L
        if (!MoveType.Standard.L_SHAPE.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérifier que la case de destination est vide ou contient une pièce ennemie
        Piece targetPiece = board.getPiece(endX, endY);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }
}
