package chess;

import chess.moves.DefaultPathValidator;
import chess.moves.MoveType;
import chess.moves.PathValidator;

public class Queen extends Piece {

    private final PathValidator pathValidator = new DefaultPathValidator();

    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        int deltaX = distanceX(end);
        int deltaY = distanceY(end);

        // La reine peut se déplacer en diagonale, horizontalement, ou verticalement
        if (!MoveType.DIAGONAL.isValid(deltaX, deltaY) &&
                !MoveType.HORIZONTAL.isValid(deltaX, deltaY) &&
                !MoveType.VERTICAL.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérification du chemin via le PathValidator
        return pathValidator.isPathClear(board, start, end) && super.canMove(board, start, end);
    }

    @Override
    public void setSquare(Square square) {
        super.setSquare(square);
    }

    // La reine n'a pas de mouvements spéciaux comme le roque ou la promotion, donc pas de SpecialMove à implémenter
}

