package chess;

import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.DefaultPathValidator;

public class Bishop extends Piece {

    private final PathValidator pathValidator = new DefaultPathValidator();

    public Bishop(PlayerColor color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {

        // Le fou peut se déplacer uniquement en diagonale
        if (!MoveType.DIAGONAL.isValid(distanceX(end), distanceY(end))) {
            return false;
        }

        // Vérification du chemin via le PathValidator
        return pathValidator.isPathClear(board, start, end) && super.canMove(board, start, end);
    }
}

