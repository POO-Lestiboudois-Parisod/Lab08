package chess.moves;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */

import chess.Board;
import chess.Square;

/**
 * Interface représentant un coup spécial dans un jeu d'échecs
 * (par exemple, roque ou prise en passant).
 */
public interface SpecialMove {
    /**
     * Vérifie si le coup spécial est valide pour l'état actuel de l'échiquier.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ du mouvement.
     * @param end la case d'arrivée du mouvement.
     * @return {@code true} si le coup spécial est valide, {@code false} sinon.
     */
    boolean isValid(Board board, Square start, Square end);

    /**
     * Exécute le coup spécial sur l'échiquier.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ du mouvement.
     * @param end la case d'arrivée du mouvement.
     */
    void execute(Board board, Square start, Square end);
}