package chess.moves;

import chess.Board;
import chess.Square;

public class DefaultPathValidator implements PathValidator {

    @Override
    public boolean isPathClear(Board board, Square start, Square end) {
        int stepX = end.getX() - start.getX();
        int stepY = end.getY() - start.getY();

        if(stepX == 0 && stepY == 0) {
            throw new IllegalArgumentException("Aucun déplacement");
        }

        int x = (stepX > 0) ? start.getX() + 1 : (stepX == 0) ? end.getX() : start.getX() - 1;
        int y = (stepY > 0) ? start.getY() + 1 : (stepY == 0) ? end.getY() : start.getY() - 1;

        while (x != end.getX() || y != end.getY()) {
            if (board.getSquare(x, y).isOccupied()) {
                return false; // Une pièce bloque le chemin
            }

            x = (stepX > 0) ? x + 1 : (stepX == 0) ? end.getX() : x - 1;
            y = (stepY > 0) ? y + 1 : (stepY == 0) ? end.getY() : y - 1;
        }

        return true;
    }
}
