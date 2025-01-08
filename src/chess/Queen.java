/*package chess;

public class Queen extends Piece {

    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Board board, int startX, int startY, int endX, int endY) {
        int deltaX = Math.abs(endX - startX);
        int deltaY = Math.abs(endY - startY);

        // Vérifier les mouvements en ligne droite ou en diagonale
        boolean isDiagonal = MoveType.Standard.DIAGONAL.isValid(deltaX, deltaY);
        boolean isLinear = MoveType.Standard.LINEAR.isValid(deltaX, deltaY);

        if (!isDiagonal && !isLinear) {
            return false;
        }

        // Vérification des obstacles
        int stepX = (endX - startX) == 0 ? 0 : (endX - startX) / Math.max(deltaX, 1);
        int stepY = (endY - startY) == 0 ? 0 : (endY - startY) / Math.max(deltaY, 1);

        int x = startX + stepX;
        int y = startY + stepY;

        while (x != endX || y != endY) {
            if (board.getPiece(x, y) != null) {
                return false; // Une pièce bloque le chemin
            }
            x += stepX;
            y += stepY;
        }

        // Vérifier que la case de destination est vide ou contient une pièce ennemie
        Piece targetPiece = board.getPiece(endX, endY);
        return targetPiece == null || targetPiece.getColor() != this.getColor();
    }

    public boolean canMove(Square square){
       if(square.isOccupied()){
           return !square.getPiece().getColor().equals(this.getColor());
       }
       return true;
    }
}*/

package chess;

import chess.moves.DefaultPathValidator;
import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.SpecialMove;

public class Queen extends Piece {

    private final PathValidator pathValidator = new DefaultPathValidator();

    public Queen(PlayerColor color) {
        super(color, PieceType.QUEEN);
    }

    @Override
    public boolean canMove(Board board, Square start, Square end) {
        int deltaX = Math.abs(end.getX() - start.getX());
        int deltaY = Math.abs(end.getY() - start.getY());

        // La reine peut se déplacer en diagonale, horizontalement, ou verticalement
        if (!MoveType.DIAGONAL.isValid(deltaX, deltaY) &&
                !MoveType.HORIZONTAL.isValid(deltaX, deltaY) &&
                !MoveType.VERTICAL.isValid(deltaX, deltaY)) {
            return false;
        }

        // Vérification du chemin via le PathValidator
        return pathValidator.isPathClear(board, start, end);
    }

    @Override
    public void setSquare(Square square) {
        super.setSquare(square);
    }

    // La reine n'a pas de mouvements spéciaux comme le roque ou la promotion, donc pas de SpecialMove à implémenter
}

