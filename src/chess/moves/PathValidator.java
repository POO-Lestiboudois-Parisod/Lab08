package chess.moves;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.Board;
import chess.Square;

/**
 * Interface pour valider si un chemin entre deux cases sur un échiquier est dégagé.
 */
public interface PathValidator {
    /**
     * Vérifie si le chemin entre deux cases sur l'échiquier est libre de toute obstruction.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le chemin est dégagé, {@code false} sinon.
     */
    boolean isPathClear(Board board, Square start, Square end);
}