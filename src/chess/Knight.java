/*package chess;

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

    @Override
    public boolean canMove(Square square) {
        return false;
        //TODO
    }
}*/

package chess;

import chess.moves.MoveType;

public class Knight extends Piece {

    public Knight(PlayerColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        int deltaX = Math.abs(end.getX() - start.getX());
        int deltaY = Math.abs(end.getY() - start.getY());

        // Le cavalier se déplace en forme de L
        return MoveType.L_SHAPE.isValid(deltaX, deltaY);
    }
}
