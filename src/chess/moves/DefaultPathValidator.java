package chess.moves;

import chess.Board;
import chess.Square;

public class DefaultPathValidator implements PathValidator {

    @Override
    public boolean isPathClear(Board board, Square start, Square end) {
        int stepX = Integer.compare(end.getX(), start.getX());
        int stepY = Integer.compare(end.getY(), start.getY());

        int x = start.getX() + stepX;
        int y = start.getY() + stepY;

        while (x != end.getX() || y != end.getY()) {
            if (board.getSquare(x, y).isOccupied()) {
                return false; // Une pièce bloque le chemin
            }
            x += stepX;
            y += stepY;
        }

        return true;
    }
}
