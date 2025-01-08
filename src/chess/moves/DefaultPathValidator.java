package chess.moves;

import chess.Board;
import chess.Square;

public class DefaultPathValidator implements PathValidator {

    @Override
    public boolean isPathClear(Board board, Square start, Square end) {
        int stepX = Integer.compare(end.getX(), start.getX());
        int stepY = Integer.compare(end.getY(), start.getY());

        if(stepX == 0 && stepY == 0) {
            throw new IllegalArgumentException("Aucun déplacement");
        }

        int x = (stepX > 0) ? start.getX() + 1 : start.getX() - 1;
        int y = (stepX > 0) ? start.getY() + 1 : start.getY() - 1;

        while (x != end.getX() || y != end.getY()) {
            if (board.getSquare(x, y).isOccupied()) {
                return false; // Une pièce bloque le chemin
            }

            x = (stepX > 0) ? x + 1 : x - 1;
            y = (stepY > 0) ? y + 1 : y - 1;
        }

        return true;
    }
}
