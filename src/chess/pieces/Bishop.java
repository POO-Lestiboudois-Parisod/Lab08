package chess.pieces;

/**
 * @author Lestiboudois Maxime & Parisod Nathan
 * @date 09/01/2025
 */
import chess.*;
import chess.moves.MoveType;
import chess.moves.PathValidator;
import chess.moves.DefaultPathValidator;

/**
 * Représente un fou dans un jeu d'échecs.
 * Le fou peut se déplacer uniquement en diagonale et son chemin doit être dégagé.
 */
public class Bishop extends Piece {

    /**
     * Validateur pour vérifier que le chemin entre deux cases est dégagé.
     */
    private final PathValidator pathValidator = new DefaultPathValidator();

    /**
     * Initialise un fou avec une couleur spécifiée.
     *
     * @param color la couleur du joueur à laquelle appartient le fou.
     */
    public Bishop(PlayerColor color) {
        super(color, PieceType.BISHOP);
    }

    /**
     * Vérifie si le fou peut se déplacer d'une case à une autre.
     *
     * @param board l'échiquier représentant l'état actuel du jeu.
     * @param start la case de départ.
     * @param end la case d'arrivée.
     * @return {@code true} si le déplacement est valide (diagonal, chemin dégagé,
     *         et respecte les règles générales des pièces), {@code false} sinon.
     */
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

