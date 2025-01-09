package chess.moves;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.Board;
import chess.Square;

/**
 * Vérifie si le chemin entre deux cases sur l'échiquier est dégagé.
 * Implémente l'interface {@link PathValidator}.
 *
 * Lève une exception {@link IllegalArgumentException} si la case de départ
 * est identique à la case d'arrivée.</p>
 */
public class DefaultPathValidator implements PathValidator {
    /**
     * Vérifie si le chemin entre deux cases est dégagé.
     *
     * @param board l'état actuel de l'échiquier.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le chemin est dégagé, {@code false} sinon.
     * @throws IllegalArgumentException si la case de départ est identique à la case d'arrivée.
     */
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
